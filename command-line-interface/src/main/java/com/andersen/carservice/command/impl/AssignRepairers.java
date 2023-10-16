package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithAllArgumentsUuid;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.constants.OrderUtil;
import com.andersen.carservice.util.constants.RepairerUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class AssignRepairers extends NamedCommandWithAllArgumentsUuid {

    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;

    public AssignRepairers(String name, OrderStorage orderStorage, RepairerStorage repairerStorage) {
        super(name);
        this.orderStorage = orderStorage;
        this.repairerStorage = repairerStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID orderId = UUID.fromString(arguments.get(1));
        orderStorage.findById(orderId).ifPresentOrElse(
                order -> arguments.stream()
                        .skip(2)
                        .forEach(element -> {
                            UUID repairerId = UUID.fromString(element);
                            repairerStorage.findById(repairerId).ifPresentOrElse(
                                    repairer -> order.addRepairer(repairer.getId()),
                                    () -> writer.write(RepairerUtil.notFoundById(repairerId))
                            );
                        }),
                () -> writer.write(OrderUtil.notFoundById(orderId))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command assigns one or more repairers to an order. ");
        writer.println("The first argument is the order's id, all further arguments are repairer's ids");
        writer.println("Format: " + name + " <order-id> <repairers-ids>");
        writer.println("Example to assign two repairers: " + name + " + c7365c9e-3cf5-490f-9c85-38e936f758e6 f7b26d57-62f1-482b-8834-41d6e72db1e4 a589fbdd-5b0a-46cb-985d-072527c50216");
    }
}
