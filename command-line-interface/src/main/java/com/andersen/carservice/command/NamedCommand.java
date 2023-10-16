package com.andersen.carservice.command;

import lombok.Getter;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class NamedCommand implements Command {

    protected String name;

    public NamedCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(List<String> arguments, PrintWriter writer) {
        if (!arguments.isEmpty() && Objects.equals(arguments.get(0), name)) {
            if (arguments.size() > 1 && Objects.equals("--help", arguments.get(1))) {
                printHelp(writer);
            } else {
                runCommand(arguments, writer);
            }
        }
    }

    protected abstract void runCommand(List<String> arguments, PrintWriter writer);


}
