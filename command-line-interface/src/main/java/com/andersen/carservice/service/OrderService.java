package com.andersen.carservice.service;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.exception.AlreadyExistsException;
import com.andersen.carservice.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void assignRepairers(UUID orderId, List<UUID> repairersIds) throws NotFoundException;

    void deleteOrder(UUID orderId) throws NotFoundException;

    Order getById(UUID orderId) throws NotFoundException;

    List<Order> getAll();

    void save(Order order);

    void changeOrderStatus(UUID orderId, OrderStatus status) throws NotFoundException;
}
