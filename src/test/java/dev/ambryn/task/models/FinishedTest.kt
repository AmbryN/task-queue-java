package dev.ambryn.task.models

import org.junit.jupiter.api.Test

internal class FinishedTest {
    @Test
    fun start_shouldNotChangeState() {
        val task = Task("test")
        task.state = Finished(task)
        task.start()
        assert(task.state is Finished)
    }

    @Test
    fun finish_shouldNotChangeState() {
        val task = Task("test")
        task.state = Finished(task)
        task.finish()
        assert(task.state is Finished)
    }

    @Test
    fun error_shouldNotChangeState() {
        val task = Task("test")
        task.state = Finished(task)
        task.error()
        assert(task.state is Finished)
    }

    @Test
    fun cancel_shouldNotChangeState() {
        val task = Task("test")
        task.state = Finished(task)
        task.cancel()
        assert(task.state is Finished)
    }
}