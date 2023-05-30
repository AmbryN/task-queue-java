package dev.ambryn.task.services;

import dev.ambryn.task.models.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskRunnerServiceTest {

    @Autowired
    TaskRunnerService trs;

    @AfterEach
    public void destroy() {
        this.trs.clearAll();
    }

    @Test
    public void addTask_shouldAddTaskToQueue() {
        List<Task> tasks = IntStream.range(0, 3)
                                    .boxed()
                                    .map(i -> new Task(String.valueOf(i)))
                                    .toList();
        this.trs.addTasks(tasks);
        assertEquals(tasks.size() - TaskRunnerService.MAX_CONCURRENT_TASKS, this.trs.sizeOfQueue());
    }

    @Test
    public void run_shouldRemoveTaskFromQueue() {
        Task task = new Task("test");
        this.trs.addTasks(List.of(task));
        assertEquals(0, this.trs.sizeOfQueue());
    }

    @Test
    public void run_shouldAddTaskToRunningTasks() {
        Task task = new Task("Test");
        this.trs.addTasks(List.of(task));
        assertEquals(1,
                     this.trs.getRunningTasks()
                             .size());
    }

    @Test
    public void run_ifBacklogBiggerAsMaxConcurrentTasks_runOnlyMaxConcurrentTasks() {
        List<Task> tasks = IntStream.range(0, 2)
                                    .boxed()
                                    .map(i -> new Task(String.valueOf(i)))
                                    .toList();
        this.trs.addTasks(tasks);

        assertEquals(TaskRunnerService.MAX_CONCURRENT_TASKS,
                     this.trs.getRunningTasks()
                             .size());
    }
}