package dev.ambryn.task.models

import dev.ambryn.task.interfaces.State

class Finished(context: Task) : State(context) {
    override var status = Status.FINISHED

    override fun start() {
        return
    }

    override fun error() {
        return
    }

    override fun finish() {
        return
    }

    override fun cancel() {
        return
    }
}
