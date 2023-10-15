package com.andersen.carservice.command;

import com.andersen.carservice.util.UuidHelper;

import java.io.PrintWriter;
import java.util.List;

public abstract class AllArgumentsUuid extends NamedCommand {

    public AllArgumentsUuid(String name) {
        super(name);
    }

    @Override
    public void execute(List<String> arguments, PrintWriter writer) {
        for (String argument : arguments) {
            if (!UuidHelper.isParsable(argument)) {
                writer.write("Expected argument of type: UUID, got: " + argument);
                return;
            }
        }
        super.execute(arguments, writer);
    }
}
