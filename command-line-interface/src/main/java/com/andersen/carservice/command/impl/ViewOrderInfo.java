package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;

import java.io.OutputStream;
import java.util.List;

public class ViewOrderInfo extends NamedCommand {
    public ViewOrderInfo(String name) {
        super(name);
    }

    @Override
    public void execute(List<String> arguments) {

    }

    @Override
    public void printHelp(OutputStream output) {

    }
}
