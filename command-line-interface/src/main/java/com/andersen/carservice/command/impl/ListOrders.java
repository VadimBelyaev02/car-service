package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.storage.OrderStorage;

import java.io.PrintWriter;
import java.util.List;

public class ListOrders extends NamedCommand {

   private final OrderStorage orderStorage;

    public ListOrders(String name, OrderStorage orderStorage) {
        super(name);
        this.orderStorage = orderStorage;
    }


    @Override
    public void printHelp(PrintWriter writer) {

    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {

    }
}
