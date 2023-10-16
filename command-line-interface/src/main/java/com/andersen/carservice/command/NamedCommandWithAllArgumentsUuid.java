package com.andersen.carservice.command;

import com.andersen.carservice.util.UuidHelper;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public abstract class NamedCommandWithAllArgumentsUuid extends NamedCommand {

    public NamedCommandWithAllArgumentsUuid(String name) {
        super(name);
    }

    @Override
    public void execute(List<String> arguments, PrintWriter writer) {
        if (!arguments.isEmpty() && Objects.equals(arguments.get(0), name)) {
            for (String argument : arguments) {
                if (!UuidHelper.isParsable(argument)) {
                    writer.println("Expected argument of type: UUID, got: " + argument);
                    return;
                }
            }
            super.execute(arguments, writer);
        }
    }
}
