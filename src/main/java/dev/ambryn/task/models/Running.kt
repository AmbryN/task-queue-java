package dev.ambryn.task.models

import dev.ambryn.task.interfaces.State
import java.time.LocalDateTime

class Running(context: Task) : State(context) {
    override var status: Status = Status.RUNNING

    override fun error() {
        context.state = Errored(context)
    }

    override fun finish() {
        context.state = Finished(context)
        context.finishedAt = LocalDateTime.now()
    }

    override fun cancel() {
        context.state = Cancelled(context)
    }
}
