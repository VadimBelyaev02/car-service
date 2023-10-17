package com.andersen.carservice.storage.impl;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.util.UuidHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStorageImpl implements OrderStorage {

    private final Map<UUID, Order> orders = new HashMap<>();
    private static OrderStorageImpl instance;

    public static OrderStorageImpl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new OrderStorageImpl();
        }
        return instance;
    }

    @Override
    public List<Order> findAll() {
        return orders.values().stream().toList();
    }

    @Override
    public Order save(Order order) {
        if (Objects.isNull(order.getId())) {
            order.setId(UuidHelper.generate());
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

    @Override
    public boolean existsById(UUID orderId) {
        return findById(orderId).isPresent();
    }
}
