package com.andersen.carservice.command.impl;

import java.io.PrintWriter;
import java.util.List;

public class Help extends NamedCommand {

    private final List<NamedCommand> commands;
    public Help(String name, List<NamedCommand> commands) {
        super(name);
        this.commands = commands;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        commands.forEach(writer::println);
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command shows available commands. ");
    }

}
