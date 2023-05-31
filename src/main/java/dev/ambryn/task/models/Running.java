package dev.ambryn.task.models;

import dev.ambryn.task.interfaces.State;

import java.time.LocalDateTime;

public class Running extends State {

    public Running(Task context) {
        this.context = context;
        this.status = Status.RUNNING;
    }

    @Override
    public void start() {
        return;
    }

    @Override
    public void error() {
        this.context.setState(new Error(context));
    }

    @Override
    public void finish() {
        this.context.setState(new Finished(context));
        this.context.setFinishedAt(LocalDateTime.now());
    }

    @Override
    public void cancel() {
        this.context.setState(new Cancelled(context));
    }
}
