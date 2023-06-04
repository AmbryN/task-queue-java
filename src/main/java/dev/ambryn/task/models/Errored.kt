package dev.ambryn.task.models

import dev.ambryn.task.interfaces.State

class Errored(context: Task) : State(context) {
    override var status: Status = Status.ERROR

    override fun start() {
        context.state = Running(context)
    }

    override fun cancel() {
        context.state = Cancelled(context)
    }
}
