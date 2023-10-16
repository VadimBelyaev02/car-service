package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithAllArgumentsUuid;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.constants.OrderUtil;
import com.andersen.carservice.util.constants.RepairerUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CancelOrder extends NamedCommandWithAllArgumentsUuid {

    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;

    public CancelOrder(String name, OrderStorage orderStorage, RepairerStorage repairerStorage) {
        super(name);
        this.orderStorage = orderStorage;
        this.repairerStorage = repairerStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID orderId = UUID.fromString(arguments.get(1));
        orderStorage.findById(orderId).ifPresentOrElse(
                order -> {
                    order.getRepairersIds().forEach(repairerId -> {
                        Optional<Repairer> repairerOptional = repairerStorage.findById(repairerId);
                        repairerOptional.ifPresentOrElse(
                                repairer -> repairer.deleteOrder(orderId),
                                () -> writer.println(RepairerUtil.notFoundById(repairerId))
                        );
                    });
                    orderStorage.deleteById(orderId);
                },
                () -> writer.write(OrderUtil.notFoundById(orderId))
        );
    }


    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command cancels one repairer. ");
        writer.println("The first and the only one argument is the order's id. ");
        writer.println("Format: " + name + " <order-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");
    }
}
