package dev.ambryn.task.interfaces

import dev.ambryn.task.models.Status
import dev.ambryn.task.models.Task

abstract class State(protected val context: Task) {
    abstract var status: Status
        protected set

    open fun start(): Unit {}

    open fun error(): Unit {}
    open fun finish(): Unit {}
    open fun cancel(): Unit {}
}
