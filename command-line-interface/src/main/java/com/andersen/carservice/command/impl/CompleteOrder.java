package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.service.OrderService;
import com.andersen.carservice.util.UuidHelper;
import com.andersen.carservice.util.constants.OrderUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class CompleteOrder extends NamedCommand {

    private final OrderService orderService;

    public CompleteOrder(String name, OrderService orderService) {
        super(name);
        this.orderService = orderService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        if (!UuidHelper.isParsable(arguments.get(1))) {
            writer.println();
        }
        UUID id = UUID.fromString(arguments.get(1));

        try {
            orderService.changeOrderStatus(id, OrderStatus.COMPLETED);
        } catch (NotFoundException e) {
            writer.println(e.getMessage());
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command completes one order. ");
        writer.println("The first and the only one argument is the order's id. ");
        writer.println("Format: " + name + " <order-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");
    }
}
