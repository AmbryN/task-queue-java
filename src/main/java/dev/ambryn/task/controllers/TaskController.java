package dev.ambryn.task.controllers;

import dev.ambryn.task.models.Task;
import dev.ambryn.task.services.TaskRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskRunnerService trs;

    @PostMapping
    public ResponseEntity<Iterable<Task>> createTask(@RequestBody List<Task> tasks) {
        this.trs.addTasks(tasks);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping
    public ResponseEntity<Iterable<Task>> getTasks(@RequestParam("status") Optional<String> oStatus) {
        if (oStatus.isPresent()) {
            String status = oStatus.get();
            switch (status) {
                case "running" -> {
                    return ResponseEntity.ok(this.trs.getRunningTasks());
                }
                case "queued" -> {
                    return ResponseEntity.ok(this.trs.getQueuedTasks());
                }
                case "finished" -> {
                    return ResponseEntity.ok(this.trs.getFinishedTasks());
                }
                case "error" -> {
                    return ResponseEntity.ok(this.trs.getErroredTasks());
                }
                case "cancelled" -> {
                    return ResponseEntity.ok(this.trs.getCancelledTasks());
                }
            }
        }

        return ResponseEntity.ok(this.trs.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {
        return ResponseEntity.ok(this.trs.getTask(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelTask(@PathVariable Integer id) {
        this.trs.cancelTask(id);
        return ResponseEntity.ok(this.trs.getTask(id));
    }
}
