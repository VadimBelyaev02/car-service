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
        if (arguments.size() > 1 && Objects.equals(arguments.get(0), name)) {
            for (int i = 1; i < arguments.size(); i++) {
                if (!UuidHelper.isParsable(arguments.get(i))) {
                    writer.println("Expected argument of type: UUID, got: " + arguments.get(i));
                    return;
                }
            }
            super.execute(arguments, writer);
        }
    }
}
