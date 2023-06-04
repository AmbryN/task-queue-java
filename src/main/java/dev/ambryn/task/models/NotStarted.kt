package dev.ambryn.task.models

import dev.ambryn.task.interfaces.State
import java.time.LocalDateTime

class NotStarted(context: Task) : State(context) {
    override var status: Status = Status.NOT_STARTED

    override fun start() {
        context.state = Running(context)
        context.startedAt = LocalDateTime.now()
    }
}
