package com.andersen.carservice.command;

public abstract class NamedCommand implements Command {

    protected String name;

    public NamedCommand(String name) {
        this.name = name;
    }
}
