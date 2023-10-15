package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithFirstArgumentUuid;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.constants.OrderConstants;
import com.andersen.carservice.util.constants.RepairerConstants;

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
                                () -> writer.println(RepairerConstants.notFoundById(repairerId))
                        );
                    });
                },
                () -> writer.println(OrderConstants.notFoundById(id))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {

    }

}
