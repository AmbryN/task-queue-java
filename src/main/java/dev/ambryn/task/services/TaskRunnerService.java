package dev.ambryn.task.services;

import dev.ambryn.task.models.Error;
import dev.ambryn.task.models.*;
import dev.ambryn.task.utils.MathUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskRunnerService {
    public static final int MAX_CONCURRENT_TASKS = 4;
    private final Queue<Task> taskQueue = new ArrayDeque<>();
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, CompletableFuture<Long>> taskHandles = new HashMap<>();
    private int currentRunningTasks = 0;

    public TaskRunnerService() {
        this.run();
    }

    public void addTasks(List<Task> tasks) {
        this.taskQueue.addAll(tasks);
        this.run();
    }

    public Collection<Task> getTasks() {
        return Stream.of(this.tasks.values()
                                   .stream()
                                   .toList(),
                         this.taskQueue.stream()
                                       .toList())
                     .flatMap(Collection::stream)
                     .collect(Collectors.toSet());
    }

    public Collection<Task> getQueuedTasks() {
        return Collections.unmodifiableCollection(this.taskQueue);
    }

    public Collection<Task> getRunningTasks() {
        return this.tasks.values()
                         .stream()
                         .filter(task -> task.getState() instanceof Running)
                         .collect(Collectors.toSet());
    }

    public Collection<Task> getFinishedTasks() {
        return this.tasks.values()
                         .stream()
                         .filter(task -> task.getState() instanceof Finished)
                         .collect(Collectors.toSet());
    }

    public Collection<Task> getErroredTasks() {
        return this.tasks.values()
                         .stream()
                         .filter(task -> task.getState() instanceof Error)
                         .collect(Collectors.toSet());
    }

    public Collection<Task> getCancelledTasks() {
        return this.tasks.values()
                         .stream()
                         .filter(task -> task.getState() instanceof Cancelled)
                         .collect(Collectors.toSet());
    }

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    public void cancelTask(int id) {
        this.taskHandles.get(id)
                        .completeExceptionally(new CancellationException("Task id=" + id + " has been canceled by " +
                                                                                 "user"));
    }

    public int sizeOfQueue() {
        return this.taskQueue.size();
    }

    private void run() {
        while (this.currentRunningTasks < MAX_CONCURRENT_TASKS && this.taskQueue.size() > 0) {
            Task task = this.taskQueue.remove();
            this.tasks.put(task.getId(), task);
            CompletableFuture<Long> taskHandle = this.runTask(task);

            taskHandle.thenAccept((result) -> {
                task.setResult(result);
                task.finish();
                task.describe();
                this.currentRunningTasks--;
                this.run();
            });
            taskHandle.exceptionally((ex) -> {
                if (ex instanceof CancellationException) {
                    System.out.println(ex.getMessage());
                    task.cancel();
                } else {
                    System.out.println(ex.getMessage());
                    task.error();
                }
                this.currentRunningTasks--;
                return null;
            });

            this.taskHandles.put(task.getId(), taskHandle);
        }
    }

    public void clearAll() {
        this.taskQueue.clear();
        this.tasks.clear();
        this.taskHandles.clear();
        this.currentRunningTasks = 0;
    }

    private CompletableFuture<Long> runTask(Task task) {
        task.start();
        this.currentRunningTasks++;
        return CompletableFuture.supplyAsync(() -> {
            try {
                return MathUtils.fib(task.getNth());
            } catch (CancellationException e) {
                throw new CompletionException(e);
            }
        });
    }
}
