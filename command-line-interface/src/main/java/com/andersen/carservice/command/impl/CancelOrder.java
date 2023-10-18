package com.andersen.carservice.command.impl;

import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.service.OrderService;
import com.andersen.carservice.util.UuidUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import static com.andersen.carservice.util.constants.GeneralConstants.UUID_IS_NOT_PARSABLE;

public class CancelOrder extends NamedCommand {

    private final OrderService orderService;

    public CancelOrder(String name, OrderService orderService) {
        super(name);
        this.orderService = orderService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        if (UuidUtil.isNotParsable(arguments.get(1))) {
            writer.println(UUID_IS_NOT_PARSABLE);
        }
        UUID orderId = UUID.fromString(arguments.get(1));

        try {
            orderService.deleteById(orderId);
            writer.println("Order with id = " + orderId + " was deleted");
        } catch (NotFoundException e) {
            writer.println(e.getMessage());
        }
    }


    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command cancels one repairer. ");
        writer.println("The first and the only one argument is the order's id. ");
        writer.println("Format: " + name + " <order-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");
    }
}
