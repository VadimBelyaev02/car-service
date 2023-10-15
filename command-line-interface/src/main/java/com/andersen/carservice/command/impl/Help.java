package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Help extends NamedCommand {

    public Help(String name) {
        super(name);
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {

    }

    @Override
    public void printHelp(PrintWriter writer) {

    }
}
