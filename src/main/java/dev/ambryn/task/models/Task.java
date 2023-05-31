package dev.ambryn.task.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.ambryn.task.interfaces.State;
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
    @JsonSerialize(using = StateSerializer.class)
    private State state;

    public Task() {
        this.id = ++numberOfTasks;
        this.createdAt = LocalDateTime.now();
        this.state = new NotStarted(this);
        this.state.setContext(this);
    }

    public Task(String name) {
        this();
        this.name = name;
        this.nth = 40;
    }

    public void start() {
        this.state.start();
    }

    public void finish() {
        this.state.finish();
    }

    public void cancel() {
        this.state.cancel();
    }

    public void error() {
        this.state.error();
    }

    public void describe() {
        System.out.println("Task " + this.id + " - Took " + ChronoUnit.SECONDS.between(startedAt,
                                                                                       finishedAt) + "s to finish! - "
                                   + "Result: " + result);
    }

    public int getNth() {
        return this.nth;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
