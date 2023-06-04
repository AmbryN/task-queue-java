package dev.ambryn.task.models

import org.junit.jupiter.api.Test

internal class ErroredTest {
    @Test
    fun start_shouldChangeStateToRunning() {
        val task = Task("test")
        task.state = Errored(task)
        task.start()
        assert(task.state is Running)
    }

    @Test
    fun cancel_shouldChangeStateToCancelled() {
        val task = Task("test")
        task.state = Errored(task)
        task.cancel()
        assert(task.state is Cancelled)
    }

    @Test
    fun finish_shouldNotChangeState() {
        val task = Task("test")
        task.state = Errored(task)
        task.finish()
        assert(task.state is Errored)
    }

    @Test
    fun error_shouldNotChangeState() {
        val task = Task("test")
        task.state = Errored(task)
        task.error()
        assert(task.state is Errored)
    }
}