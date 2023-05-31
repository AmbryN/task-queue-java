package dev.ambryn.task.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class Task {
    private static Integer numberOfTasks = 0;
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private int nth;
    private Long result;
    private Status status;

    public Task() {
        this.id = ++numberOfTasks;
        this.createdAt = LocalDateTime.now();
        this.status = Status.NOT_STARTED;
    }

    public Task(String name) {
        this();
        this.name = name;
    }

    public void start() {
        this.startedAt = LocalDateTime.now();
        this.status = Status.RUNNING;
    }

    public void finish() {
        this.finishedAt = LocalDateTime.now();
        this.status = Status.FINISHED;
    }

    public void cancel() {
        this.status = Status.CANCELED;
    }

    public void describe() {
        System.out.println("Task " + this.id + " - Took " + ChronoUnit.SECONDS.between(startedAt,
                                                                                       finishedAt) + "s to finish! - "
                                   + "Result: " + result);
    }

    public void error() {
        this.status = Status.ERROR;
    }

    public int getNth() {
        return this.nth;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
