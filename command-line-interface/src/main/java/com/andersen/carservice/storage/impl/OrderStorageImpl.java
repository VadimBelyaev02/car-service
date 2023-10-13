package com.andersen.carservice.storage.impl;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.util.UuidGenerator;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class OrderStorageImpl implements OrderStorage {

    private final Map<UUID, Order> orders;

    @Override
    public Order save(Order order) {
        if (Objects.isNull(order.getId())) {
            order.setId(UuidGenerator.generate());
        }
        return orders.put(order.getId(), order);
    }

    @Override
    public void deleteById(UUID id) {
        orders.remove(id);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }
}
