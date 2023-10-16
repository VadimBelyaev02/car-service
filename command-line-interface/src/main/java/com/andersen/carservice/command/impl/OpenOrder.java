package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommandWithAllArgumentsUuid;
import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.util.UuidHelper;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class OpenOrder extends NamedCommandWithAllArgumentsUuid {

    private final OrderStorage orderStorage;

    public OpenOrder(String name, OrderStorage orderStorage) {
        super(name);
        this.orderStorage = orderStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        UUID id = UuidHelper.generate();
        BigDecimal price = new BigDecimal(arguments.get(1));
        Instant openingDate = Instant.now();
        OrderStatus status = OrderStatus.ACTIVE;

        Order order = Order.builder()
                .id(id)
                .price(price)
                .openingDate(openingDate)
                .status(status)
                .build();

        Order savedOrder = orderStorage.save(order);
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command opens one order. ");
        writer.println("The first argument is a price and that's all. ");
        writer.println("Format: " + name + " <price> ");
        writer.println("Example: " + name + " 12.00 ");
    }
}
