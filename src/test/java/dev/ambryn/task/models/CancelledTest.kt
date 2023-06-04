package dev.ambryn.task.models

import org.junit.jupiter.api.Test

internal class CancelledTest {
    @Test
    fun start_shouldChangeStateToRunning() {
        val task = Task("test")
        task.state = Cancelled(task)
        task.start()
        assert(task.state is Running)
    }

    @Test
    fun error_shouldNotChangeState() {
        val task = Task("test")
        task.state = Cancelled(task)
        task.error()
        assert(task.state is Cancelled)
    }

    @Test
    fun finish_shouldNotChangeState() {
        val task = Task("test")
        task.state = Cancelled(task)
        task.finish()
        assert(task.state is Cancelled)
    }

    @Test
    fun cancel_shouldNotChangeState() {
        val task = Task("test")
        task.state = Cancelled(task)
        task.cancel()
        assert(task.state is Cancelled)
    }
}