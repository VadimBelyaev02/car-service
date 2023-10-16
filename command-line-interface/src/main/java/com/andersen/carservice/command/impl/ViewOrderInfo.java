package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithFirstArgumentUuid;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.constants.OrderUtil;
import com.andersen.carservice.util.constants.RepairerUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ViewOrderInfo extends NamedCommandWithFirstArgumentUuid {

    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;

    public ViewOrderInfo(String name, OrderStorage orderStorage, RepairerStorage repairerStorage) {
        super(name);
        this.orderStorage = orderStorage;
        this.repairerStorage = repairerStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID id = UUID.fromString(arguments.get(1));
        orderStorage.findById(id).ifPresentOrElse(
                order -> {
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
                        repairerStorage.findById(repairerId).ifPresentOrElse(
                                writer::println,
                                () -> writer.println(RepairerUtil.notFoundById(repairerId))
                        );
                    });
                },
                () -> writer.println(OrderUtil.notFoundById(id))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command shows detailed info about a particular order. ");
        writer.println("The first argument is the order's id. ");
        writer.println("Format: " + name + " <order-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");
    }

}
