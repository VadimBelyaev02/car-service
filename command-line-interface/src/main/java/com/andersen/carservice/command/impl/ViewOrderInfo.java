package com.andersen.carservice.command.impl;

import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.model.response.OrderResponse;
import com.andersen.carservice.service.OrderService;
import com.andersen.carservice.service.impl.RepairerServiceImpl;
import com.andersen.carservice.util.UuidUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ViewOrderInfo extends NamedCommand {

    private final OrderService orderService;

    public ViewOrderInfo(String name, OrderService orderService) {
        super(name);
        this.orderService = orderService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        String firstParameter = arguments.get(1);
        if (UuidUtil.isNotParsable(firstParameter)) {
            writer.println(UuidUtil.uuidIsNotParsable(firstParameter));
        }
        UUID orderId = UUID.fromString(firstParameter);
        try {
            OrderResponse order = orderService.getById(orderId);
            int i = 1;
            writer.println("Order info: ");
            writer.println(i++ + ") Id: " + order.getId());
            writer.println(i++ + ") Price: " + order.getPrice());
            writer.println(i++ + ") Status: " + order.getStatus());
            writer.println(i++ + ") Opening date: " + order.getOpeningDate());
            if (Objects.nonNull(order.getCompletionDate())) {
                writer.println(i + ") Completion date: " + order.getCompletionDate());
            }
            if (!order.getRepairers().isEmpty()) {
                writer.println("Repairers:");
            }
            order.getRepairers().forEach(writer::println);
        } catch (NotFoundException e) {
            writer.println(e.getMessage());
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command shows detailed info about a particular order. ");
        writer.println("The first argument is the order's id. ");
        writer.println("Format: " + name + " <order-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");
    }

}
