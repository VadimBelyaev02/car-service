package com.andersen.carservice.command;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public abstract class NamedCommand implements Command {

    protected String name;

    public NamedCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(List<String> arguments, PrintWriter writer) {
        if (arguments.size() > 1 && Objects.equals(arguments.get(0), name)) {
            if (Objects.equals("--help", arguments.get(1))) {
                printHelp(writer);
            } else {
                runCommand(arguments, writer);
            }
        }
    }

    protected abstract void runCommand(List<String> arguments, PrintWriter writer);
}
