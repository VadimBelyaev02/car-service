package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithFirstArgumentUuid;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.constants.RepairerConstants;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FireRepairer extends NamedCommandWithFirstArgumentUuid {

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
        Optional<Repairer> repairerOptional = repairerStorage.findById(repairerId);
        repairerOptional.ifPresentOrElse(
                repairer -> {
                    repairer.getOrdersIds().forEach(orderId -> {
                        orderStorage.findById(orderId).ifPresentOrElse(
                                order -> order.deleteRepairer(repairerId),
                                () -> writer.println(RepairerConstants.notFoundById(repairerId))
                        );
                    });
                    repairerStorage.deleteById(repairerId);
                },
                () -> writer.println(RepairerConstants.notFoundById(repairerId))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command fires one repairer. ");
        writer.println("The first and the only one argument is the repairer's id. ");
        writer.println("Format: fire-repairer <repairer-id>");
        writer.println("Example: fire-repairer c7365c9e-3cf5-490f-9c85-38e936f758e6");

    }
}
