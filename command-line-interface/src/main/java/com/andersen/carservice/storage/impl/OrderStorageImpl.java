package com.andersen.carservice.storage.impl;

import com.andersen.carservice.model.entity.Order;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.util.UuidUtil;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class OrderStorageImpl implements OrderStorage {

    private final Map<UUID, Order> orders = new HashMap<>();

    @Override
    public List<Order> findAll() {
        return orders.values().stream().toList();
    }

    @Override
    public Order save(Order order) {
        if (Objects.isNull(order.getId())) {
            order.setId(UuidUtil.generate());
        }
        orders.put(order.getId(), order);
        return order;
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
