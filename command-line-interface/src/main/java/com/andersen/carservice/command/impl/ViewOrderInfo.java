package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.Order;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.service.OrderService;
import com.andersen.carservice.service.RepairerService;
import com.andersen.carservice.util.constants.RepairerUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ViewOrderInfo extends NamedCommand {

    private final OrderService orderService;
    private final RepairerService repairerService;

    public ViewOrderInfo(String name, OrderService orderService, RepairerService repairerService) {
        super(name);
        this.orderService = orderService;
        this.repairerService = repairerService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID orderId = UUID.fromString(arguments.get(1));
        try {
            Order order = orderService.getById(orderId);
            int i = 1;
            writer.println("Order info: ");
            writer.println(i++ + ") Id: " + order.getId());
            writer.println(i++ + ") Price: " + order.getPrice());
            writer.println(i++ + ") Status: " + order.getStatus());
            writer.println(i++ + ") Opening date: " + order.getOpeningDate());
            if (Objects.nonNull(order.getCompletionDate())) {
                writer.println(i + ") Completion date: " + order.getCompletionDate());
            }
            order.getRepairersIds().forEach(repairerId -> {
                repairerService.getById(repairerId).ifPresentOrElse(
                        writer::println,
                        () -> writer.println(RepairerUtil.notFoundById(repairerId))
                );
            });
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
