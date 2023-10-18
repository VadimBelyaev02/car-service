package com.andersen.carservice.mapper;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.entity.enums.RepairerStatus;
import com.andersen.carservice.request.RepairerRequest;
import com.andersen.carservice.response.RepairerResponse;
import com.andersen.carservice.storage.OrderStorage;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RepairerMapper {

    private final OrderStorage orderStorage;

    public RepairerResponse toResponse(Repairer repairer) {
        final String name = repairer.getName();
        final RepairerStatus status = repairer.getStatus();
        final String email = repairer.getEmail();
        final UUID id = repairer.getId();
        final List<Order> orders = new ArrayList<>();
        repairer.getOrdersIds().forEach(orderId -> {
            orderStorage.findById(orderId).ifPresent(orders::add);
        });


        return RepairerResponse.builder()
                .id(id)
                .email(email)
                .status(status)
                .name(name)
                .orders(orders)
                .build();
    }

    public Repairer toEntity(RepairerRequest repairerRequest) {
        final String name = repairerRequest.getName();
        final RepairerStatus status = repairerRequest.getStatus();
        final String email = repairerRequest.getEmail();


        return Repairer.builder()
                .status(status)
                .email(email)
                .name(name)
                .build();
    }
}
