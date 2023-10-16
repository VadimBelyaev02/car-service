package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithFirstArgumentUuid;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.util.constants.OrderUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class CompleteOrder extends NamedCommandWithFirstArgumentUuid {

    private final OrderStorage orderStorage;

    public CompleteOrder(String name, OrderStorage orderStorage) {
        super(name);
        this.orderStorage = orderStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID id = UUID.fromString(arguments.get(1));
        orderStorage.findById(id).ifPresentOrElse(
                order -> order.setStatus(OrderStatus.COMPLETED),
                () -> writer.write(OrderUtil.notFoundById(id))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command completes one order. ");
        writer.println("The first and the only one argument is the order's id. ");
        writer.println("Format: " + name + " <order-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");
    }
}
