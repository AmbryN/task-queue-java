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
    private int durationInSeconds;
    private Status status;

    public Task() {
        this.id = ++numberOfTasks;
        this.createdAt = LocalDateTime.now();
        this.status = Status.NOT_STARTED;
        this.durationInSeconds = 10;
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
                                                                                       finishedAt) + "s to finish!");
    }

    public void error() {
        this.status = Status.ERROR;
    }
}
