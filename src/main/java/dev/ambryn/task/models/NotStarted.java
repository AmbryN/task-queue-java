package dev.ambryn.task.models;

import dev.ambryn.task.interfaces.State;

import java.time.LocalDateTime;

public class NotStarted extends State {

    public NotStarted(Task context) {
        this.context = context;
        this.status = Status.NOT_STARTED;
    }

    public void start() {
        this.context.setState(new Running(context));
        this.context.setStartedAt(LocalDateTime.now());
    }

    @Override
    public void error() {
        return;
    }

    @Override
    public void finish() {
        return;
    }

    @Override
    public void cancel() {
        return;
    }
}
