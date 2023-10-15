package com.andersen.carservice.command;

import com.andersen.carservice.util.UuidHelper;

import java.io.PrintWriter;
import java.util.List;

public abstract class FirstArgumentUuid extends NamedCommand {

    public FirstArgumentUuid(String name) {
        super(name);
    }

    @Override
    public void execute(List<String> arguments, PrintWriter writer) {
        if (arguments.size() > 1 && UuidHelper.isParsable(arguments.get(1))) {
            super.execute(arguments, writer);
        } else {
            writer.write("First argument must be UUID");
        }

    }
}