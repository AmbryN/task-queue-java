package dev.ambryn.task.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.ambryn.task.models.Status;
import dev.ambryn.task.models.Task;

public abstract class State {
    @JsonIgnore
    protected Task context;
    protected Status status;

    public void setContext(Task context) {
        this.context = context;
    }

    public Status getStatus() {
        return this.status;
    }

    public abstract void start();

    public abstract void error();

    public abstract void finish();

    public abstract void cancel();
}
