package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;

import java.io.OutputStream;
import java.util.List;

public class OpenOrder extends NamedCommand {
    public OpenOrder(String name) {
        super(name);
    }

    @Override
    public void execute(List<String> arguments) {

    }

    @Override
    public void printHelp(OutputStream output) {

    }
}
