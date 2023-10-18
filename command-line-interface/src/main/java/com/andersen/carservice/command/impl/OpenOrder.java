package com.andersen.carservice.command.impl;

import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.model.request.OrderRequest;
import com.andersen.carservice.model.response.OrderResponse;
import com.andersen.carservice.service.OrderService;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class OpenOrder extends NamedCommand {

    private final OrderService orderService;

    public OpenOrder(String name, OrderService orderService) {
        super(name);
        this.orderService = orderService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        BigDecimal price = new BigDecimal(arguments.get(1));

        OrderRequest orderRequest = OrderRequest.builder()
                .price(price)
                .build();

        try {
            OrderResponse orderResponse = orderService.save(orderRequest);
            writer.println("Order saved: " + orderResponse);
        } catch (NotFoundException e) {
            writer.println(e.getMessage());
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command opens one order. ");
        writer.println("The first argument is a price and that's all. ");
        writer.println("Format: " + name + " <price> ");
        writer.println("Example: " + name + " 12.00 ");
    }
}
