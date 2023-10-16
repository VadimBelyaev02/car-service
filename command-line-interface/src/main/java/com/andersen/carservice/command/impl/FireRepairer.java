package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithAllArgumentsUuid;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.constants.RepairerUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class FireRepairer extends NamedCommandWithAllArgumentsUuid {

    private final RepairerStorage repairerStorage;
    private final OrderStorage orderStorage;

    public FireRepairer(String name, RepairerStorage repairerStorage, OrderStorage orderStorage) {
        super(name);
        this.repairerStorage = repairerStorage;
        this.orderStorage = orderStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID repairerId = UUID.fromString(arguments.get(1));
        repairerStorage.findById(repairerId).ifPresentOrElse(
                repairer -> {
                    repairer.getOrdersIds().forEach(orderId -> {
                        orderStorage.findById(orderId).ifPresentOrElse(
                                order -> order.deleteRepairer(repairerId),
                                () -> writer.println(RepairerUtil.notFoundById(repairerId))
                        );
                    });
                    repairerStorage.deleteById(repairerId);
                },
                () -> writer.println(RepairerUtil.notFoundById(repairerId))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command fires one repairer. ");
        writer.println("The first and the only one argument is the repairer's id. ");
        writer.println("Format: " + name + " <repairer-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");

    }
}
