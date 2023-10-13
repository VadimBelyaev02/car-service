package com.andersen.carservice.storage.impl;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.storage.OrderStorage;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class OrderStorageImpl implements OrderStorage {

    private final Map<Order, UUID> orders;

    @Override
    public Order save(Order object) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.empty();
    }
}
