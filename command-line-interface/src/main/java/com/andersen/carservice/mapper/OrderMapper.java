package com.andersen.carservice.mapper;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.request.OrderRequest;
import com.andersen.carservice.response.OrderResponse;
import com.andersen.carservice.response.RepairerResponse;
import com.andersen.carservice.storage.RepairerStorage;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class OrderMapper {

    private final RepairerStorage repairerStorage;

    public OrderResponse toResponse(Order order) {
        final BigDecimal price = order.getPrice();
        final UUID id = order.getId();
        final OrderStatus orderStatus = order.getStatus();
        final Instant openingDate = order.getOpeningDate();
        final Instant completionDate = order.getCompletionDate();


        return OrderResponse.builder()
                .price(price)
                .id(id)
                .status(orderStatus)
                .openingDate(openingDate)
                .completionDate(completionDate)
                .build();
    }

    public Order toEntity(OrderRequest orderRequest) {
        final BigDecimal price = orderRequest.getPrice();
        final OrderStatus status = orderRequest.getStatus();
        final List<UUID> repairersIds = orderRequest.getRepairersIds();

        return Order.builder()
                .price(price)
                .status(status)
                .repairersIds(repairersIds)
                .build();
    }

    public void updateEntity(Order target, OrderRequest orderRequest) {
        if (Objects.nonNull(orderRequest.getCompletionDate())) {
            target.setCompletionDate(orderRequest.getCompletionDate());
        }
        if (Objects.nonNull(orderRequest.getPrice())) {
            target.setPrice(orderRequest.getPrice());
        }
        if (Objects.nonNull(orderRequest.getStatus())) {
            target.setStatus(orderRequest.getStatus());
        }
        if (Objects.nonNull(orderRequest.getRepairersIds())) {
            target.setRepairersIds(orderRequest.getRepairersIds());
        }
    }
}
