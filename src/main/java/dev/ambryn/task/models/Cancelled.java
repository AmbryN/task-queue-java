package dev.ambryn.task.models;

import dev.ambryn.task.interfaces.State;

public class Cancelled extends State {

    public Cancelled(Task context) {
        this.context = context;
        this.status = Status.CANCELED;
    }

    @Override
    public void start() {
        this.context.setState(new Running(context));
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
