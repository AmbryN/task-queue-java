package dev.ambryn.task.models

import dev.ambryn.task.interfaces.State

class Cancelled(context: Task) : State(context) {
    override var status: Status = Status.CANCELED

    override fun start() {
        context.state = Running(context)
    }
}
