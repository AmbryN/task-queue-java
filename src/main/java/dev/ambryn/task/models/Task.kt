package dev.ambryn.task.models

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import dev.ambryn.task.interfaces.State
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Task() {
    var name: String = ""
    val id: Int = ++numberOfTasks
    val createdAt: LocalDateTime = LocalDateTime.now()
    var startedAt: LocalDateTime? = null
    var finishedAt: LocalDateTime? = null
    var nth = 40
    var result: Long? = null

    constructor(name: String, nth: Int) : this() {
        this.name = name
        this.nth = nth
    }

    @JsonSerialize(using = StateSerializer::class)
    var state: State = NotStarted(this)

    fun start() {
        state.start()
    }

    fun finish() {
        state.finish()
    }

    fun cancel() {
        state.cancel()
    }

    fun error() {
        state.error()
    }

    fun describe() {
        println(
            "Task $id - Took " + ChronoUnit.SECONDS.between(
                startedAt,
                finishedAt
            ) + "s to finish! - "
                    + "Result: " + result
        )
    }

    companion object {
        private var numberOfTasks = 0
    }
}
