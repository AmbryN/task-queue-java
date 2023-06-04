package dev.ambryn.task.models

import org.junit.jupiter.api.Test

internal class NotStartedTest {
    @Test
    fun start_shouldChangeTheStateOfTheTaskToRunning() {
        val task = Task("test")
        task.start()
        assert(task.state is Running)
    }

    @Test
    fun cancel_error_finish_shouldNotChangeTheStateOfTheTask() {
        val task = Task("test")
        task.cancel()
        assert(task.state is NotStarted)
        task.error()
        assert(task.state is NotStarted)
        task.finish()
        assert(task.state is NotStarted)
    }
}