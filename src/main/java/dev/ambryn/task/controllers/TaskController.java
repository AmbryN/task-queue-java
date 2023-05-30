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
    public ResponseEntity<List<Task>> createTask(@RequestBody List<Task> tasks) {
        this.trs.addTasks(tasks);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping
    public ResponseEntity<Iterable<Task>> getTasks(@RequestParam("type") Optional<String> type) {
        if (type.isPresent()) {
            if (type.get()
                    .equals("running")) {
                return ResponseEntity.ok(this.trs.getRunningTasks());
            } else if (type.get()
                           .equals("queued")) {
                return ResponseEntity.ok(this.trs.getQueuedTasks());
            } else if (type.get()
                           .equals("finished")) {
                return ResponseEntity.ok(this.trs.getFinishedTasks());
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
