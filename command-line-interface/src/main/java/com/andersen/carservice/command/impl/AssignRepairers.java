package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.FirstArgumentUuid;
import com.andersen.carservice.entity.Order;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;
import com.andersen.carservice.util.constants.OrderConstants;
import com.andersen.carservice.util.constants.RepairerConstants;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AssignRepairers extends FirstArgumentUuid {

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
        Optional<Order> orderOptional = orderStorage.findById(orderId);
        orderOptional.ifPresentOrElse(
                order -> arguments.stream()
                        .skip(2)
                        .forEach(element -> {
                            if (UuidHelper.isParsable(element)) {
                                UUID repairerId = UUID.fromString(element);
                                repairerStorage.findById(repairerId).ifPresentOrElse(
                                        repairer -> order.addRepairer(repairer.getId()),
                                        () -> writer.write(RepairerConstants.notFoundById(repairerId))
                                );
                            }
                        }),
                () -> writer.write(OrderConstants.notFoundById(orderId))
        );
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command assigns one or more repairers to an order. ");
        writer.println("The first argument is an order's id, all further arguments are repairer's ids");
        writer.println("Format: assign-repairer <order-id> <repairers-ids>");
        writer.println("Example to assign two repairers: assign-repairer c7365c9e-3cf5-490f-9c85-38e936f758e6 f7b26d57-62f1-482b-8834-41d6e72db1e4 a589fbdd-5b0a-46cb-985d-072527c50216");

    }
}
