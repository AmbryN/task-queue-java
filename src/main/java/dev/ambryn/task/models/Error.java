package dev.ambryn.task.models;

import dev.ambryn.task.interfaces.State;

public class Error extends State {

    public Error(Task context) {
        this.context = context;
        this.status = Status.ERROR;
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
        this.context.setState(new Cancelled(context));
    }
}
