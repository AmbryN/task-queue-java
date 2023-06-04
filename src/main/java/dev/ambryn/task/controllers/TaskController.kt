package dev.ambryn.task.controllers

import dev.ambryn.task.models.*
import dev.ambryn.task.services.TaskRunnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController {
    @Autowired
    lateinit var trs: TaskRunnerService

    @PostMapping
    fun createTask(@RequestBody tasks: List<Task>?): ResponseEntity<Iterable<Task>?> {
        trs!!.addTasks(tasks)
        return ResponseEntity.ok(tasks)
    }

    @GetMapping
    fun getTasks(@RequestParam("status") oStatus: Optional<String?>): ResponseEntity<Collection<Task?>?> {
        if (oStatus.isPresent) {
            val status = oStatus.get()
            when (status) {
                "running" -> {
                    return ResponseEntity.ok(trs.runningTasks)
                }

                "queued" -> {
                    return ResponseEntity.ok(trs.queuedTasks)
                }

                "finished" -> {
                    return ResponseEntity.ok(trs.finishedTasks)
                }

                "error" -> {
                    return ResponseEntity.ok(trs.erroredTasks)
                }

                "cancelled" -> {
                    return ResponseEntity.ok(trs.cancelledTasks)
                }
            }
        }
        return ResponseEntity.ok(trs.getTasks())
    }

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: Int): ResponseEntity<Task?> {
        return ResponseEntity.ok(trs.getTask(id))
    }

    @DeleteMapping("/{id}")
    fun cancelTask(@PathVariable id: Int): ResponseEntity<*> {
        trs!!.cancelTask(id)
        return ResponseEntity.ok(trs.getTask(id))
    }
}
