package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.Order;
import com.andersen.carservice.service.OrderService;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class ListOrders extends NamedCommand {

    private final OrderService orderService;

    public ListOrders(String name, OrderService orderService) {
        super(name);
        this.orderService = orderService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        Comparator<Order> comparator = Comparator
                .comparing(Order::getId)
                .thenComparing(Order::getPrice)
                .thenComparing(Order::getOpeningDate)
                .thenComparing(Order::getCompletionDate)
                .thenComparing(Order::getStatus);

        List<Order> orders = orderService.getAll().stream()
                .sorted(comparator)
                .toList();

        for (int i = 0; i < orders.size(); i++) {
            writer.println((i + 1) + ") " + orders.get(i));
        }
        if (orders.isEmpty()) {
            writer.println("There are no orders. ");
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command shows all orders. It doesn't have any arguments. ");
        writer.println("Example: " + name);
    }
}
