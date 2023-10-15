package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.command.NamedCommandWithFirstArgumentUuid;
import com.andersen.carservice.storage.OrderStorage;

import java.io.PrintWriter;
import java.util.List;

public class OpenOrder extends NamedCommandWithFirstArgumentUuid {

    private final OrderStorage orderStorage;

    public OpenOrder(String name, OrderStorage orderStorage) {
        super(name);
        this.orderStorage = orderStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {

    }

    @Override
    public void printHelp(PrintWriter writer) {

    }
}
