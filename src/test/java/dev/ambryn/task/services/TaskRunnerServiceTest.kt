package dev.ambryn.task.services

import dev.ambryn.task.models.Task
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.List
import java.util.stream.IntStream

@SpringBootTest
internal class TaskRunnerServiceTest {
    @Autowired
    lateinit var trs: TaskRunnerService

    @AfterEach
    fun destroy() {
        trs.clearAll()
    }

    @Test
    fun addTask_shouldAddTaskToQueue() {
        val tasks = IntStream.range(0, TaskRunnerService.MAX_CONCURRENT_TASKS + 1)
            .boxed()
            .map { i: Int -> Task(i.toString()) }
            .toList()
        trs.addTasks(tasks)
        Assertions.assertEquals(tasks.size - TaskRunnerService.MAX_CONCURRENT_TASKS, trs.sizeOfQueue())
    }

    @Test
    fun run_shouldRemoveTaskFromQueue() {
        val task = Task("test")
        trs.addTasks(List.of(task))
        Assertions.assertEquals(0, trs.sizeOfQueue())
    }

    @Test
    fun run_shouldAddTaskToRunningTasks() {
        val task = Task("Test")
        trs.addTasks(List.of(task))
        Assertions.assertEquals(
            1,
            trs.runningTasks
                .size
        )
    }

    @Test
    fun run_ifBacklogBiggerAsMaxConcurrentTasks_runOnlyMaxConcurrentTasks() {
        val tasks = IntStream.range(0, TaskRunnerService.MAX_CONCURRENT_TASKS + 1)
            .boxed()
            .map { i: Int -> Task(i.toString()) }
            .toList()
        trs.addTasks(tasks)
        Assertions.assertEquals(
            TaskRunnerService.MAX_CONCURRENT_TASKS,
            trs.runningTasks
                .size
        )
    }
}