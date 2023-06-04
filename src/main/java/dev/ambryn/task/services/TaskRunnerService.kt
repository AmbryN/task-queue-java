package dev.ambryn.task.services

import dev.ambryn.task.models.*
import dev.ambryn.task.utils.MathUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CancellationException
import java.util.stream.Collectors
import java.util.stream.Stream

@Service
class TaskRunnerService {
    private val taskQueue: Queue<Task> = ArrayDeque()
    private val tasks: MutableMap<Int, Task> = HashMap()
    private val taskHandles: MutableMap<Int, Job> = HashMap()
    private var currentRunningTasks = 0

    fun addTasks(tasks: List<Task>?) {
        taskQueue.addAll(tasks!!)
        this.run()
    }

    fun getTasks(): Collection<Task> {
        return Stream.of(
            tasks.values.stream().toList(), taskQueue.stream().toList()
        ).flatMap { obj: List<Task> -> obj.stream() }.collect(Collectors.toSet())
    }

    val queuedTasks: Collection<Task>
        get() = Collections.unmodifiableCollection(taskQueue)
    val runningTasks: Collection<Task>
        get() = tasks.values.stream().filter { task: Task -> task.state is Running }.collect(Collectors.toSet())
    val finishedTasks: Collection<Task>
        get() = tasks.values.stream().filter { task: Task -> task.state is Finished }.collect(Collectors.toSet())
    val erroredTasks: Collection<Task>
        get() = tasks.values.stream().filter { task: Task -> task.state is Errored }.collect(Collectors.toSet())
    val cancelledTasks: Collection<Task>
        get() = tasks.values.stream().filter { task: Task -> task.state is Cancelled }.collect(Collectors.toSet())

    fun getTask(id: Int): Task? {
        return tasks[id]
    }

    fun cancelTask(id: Int) {
        println("Canceling task id=${id}")
        tasks[id]?.cancel()
        taskQueue.removeIf { task: Task -> task.id == id }
        taskHandles[id]?.cancel(CancellationException("Task id=${id} has been canceled by user"))
    }

    fun sizeOfQueue(): Int {
        return taskQueue.size
    }

    private fun run() {
        while (currentRunningTasks < MAX_CONCURRENT_TASKS && taskQueue.size > 0) {
            val task = taskQueue.remove()
            tasks[task.id] = task

            val taskHandle: Job = GlobalScope.launch {
                runTaskAsync(task)
            }

            taskHandles[task.id] = taskHandle
        }
    }

    fun clearAll() {
        taskQueue.clear()
        tasks.clear()
        taskHandles.clear()
        currentRunningTasks = 0
    }

    private suspend fun runTaskAsync(task: Task): Unit {
        task.start()
        currentRunningTasks++

        task.result = MathUtils.fib(task.nth)
        task.finish()
        task.describe()
        currentRunningTasks--

    }

    companion object {
        const val MAX_CONCURRENT_TASKS = 4
    }
}
