package dev.ambryn.task.models;

import dev.ambryn.task.interfaces.State;

public class Finished extends State {

    public Finished(Task context) {
        this.context = context;
        this.status = Status.FINISHED;
    }

    @Override
    public void start() {
        return;
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
