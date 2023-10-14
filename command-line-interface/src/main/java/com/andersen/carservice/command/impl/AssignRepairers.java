package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.Command;
import com.andersen.carservice.command.NamedCommand;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class AssignRepairers extends NamedCommand {

    public AssignRepairers(String name) {
        super(name);
    }

    @Override
    public void execute(List<String> arguments) {
        if (!arguments.isEmpty() && name.equals(arguments.get(0))) {
            runCommand(arguments);
        }
    }

    @Override
    protected void runCommand(List<String> arguments) {

    }

    @Override
    public void printHelp(OutputStream output) {

    }
}
