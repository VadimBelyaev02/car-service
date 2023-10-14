package com.andersen.carservice.command;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

public abstract class NamedCommand implements Command {

    protected String name;

    public NamedCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(List<String> arguments) {
        if (!arguments.isEmpty() && Objects.equals(arguments.get(0), name)) {
            runCommand(arguments);
        }
    }

    protected abstract void runCommand(List<String> arguments);
}
