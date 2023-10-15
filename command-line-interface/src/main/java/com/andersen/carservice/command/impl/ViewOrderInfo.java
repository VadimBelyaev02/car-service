package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.command.NamedCommandWithFirstArgumentUuid;
import com.andersen.carservice.entity.Order;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.util.constants.OrderConstants;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ViewOrderInfo extends NamedCommandWithFirstArgumentUuid {

    private final OrderStorage orderStorage;

    public ViewOrderInfo(String name, OrderStorage orderStorage) {
        super(name);
        this.orderStorage = orderStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID id = UUID.fromString(arguments.get(1));
        Optional<Order> orderOptional = orderStorage.findById(id);
        orderOptional.ifPresentOrElse(
                writer::println,
                () -> writer.println(OrderConstants.notFoundById(id))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {

    }

}
