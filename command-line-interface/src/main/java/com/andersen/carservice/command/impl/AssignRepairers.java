package com.andersen.carservice.command.impl;

import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.model.request.OrderRequest;
import com.andersen.carservice.service.OrderService;
import com.andersen.carservice.util.UuidUtil;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssignRepairers extends NamedCommand {

    private final OrderService orderService;

    public AssignRepairers(String name, OrderService orderService) {
        super(name);
        this.orderService = orderService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        List<UUID> repairersIds = new ArrayList<>();
        for (int i = 1; i < arguments.size(); i++) {
            if (UuidUtil.isNotParsable(arguments.get(i))) {
                writer.println(UuidUtil.uuidIsNotParsable(arguments.get(i)));
                return;
            }
            if (i > 1) {
                repairersIds.add(UUID.fromString(arguments.get(i)));
            }
        }
        UUID orderId = UUID.fromString(arguments.get(1));
        try {
            OrderRequest orderRequest = OrderRequest.builder()
                    .repairersIds(repairersIds)
                    .build();
            orderService.update(orderId, orderRequest);
        } catch (NotFoundException e) {
            writer.println(e.getMessage());
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command assigns one or more repairers to an order. ");
        writer.println("The first argument is the order's id, all further arguments are repairer's ids");
        writer.println("Format: " + name + " <order-id> <repairers-ids> ");
        writer.println("Example to assign two repairers: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6 f7b26d57-62f1-482b-8834-41d6e72db1e4 a589fbdd-5b0a-46cb-985d-072527c50216");
    }
}
