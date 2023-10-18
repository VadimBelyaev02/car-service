package com.andersen.carservice.storage;

import com.andersen.carservice.model.entity.Order;

import java.util.UUID;

public interface OrderStorage extends Storage<Order, UUID> {

    boolean existsById(UUID orderId);
}
