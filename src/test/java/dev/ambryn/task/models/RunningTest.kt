package dev.ambryn.task.models

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class RunningTest {
    @Test
    fun cancel_shouldChangeTheStateOfTheTaskToCancelled() {
        val task = Task("test")
        task.start()
        task.cancel()
        assert(task.state is Cancelled)
    }

    @Test
    fun finish_shouldChangeTheStateOfTheTaskToFinished() {
        val task = Task("test")
        task.start()
        task.finish()
        assert(task.state is Finished)
    }

    @Test
    fun error_shouldChangeTheStateOfTheTaskToErrored() {
        val task = Task("test")
        task.start()
        task.error()
        assert(task.state is Errored)
    }

    @Test
    fun start_shouldNotChangeTheStateOfTheTask() {
        val task = Task("test")
        task.start()
        task.start()
        assert(task.state is Running)
    }
}