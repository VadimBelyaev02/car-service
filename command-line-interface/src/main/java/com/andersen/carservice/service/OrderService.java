package com.andersen.carservice.service;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.exception.AlreadyExistsException;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.request.OrderRequest;
import com.andersen.carservice.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

  //  OrderResponse assignRepairers(UUID orderId, List<UUID> repairersIds) throws NotFoundException;

    void deleteById(UUID orderId) throws NotFoundException;

    OrderResponse getById(UUID orderId) throws NotFoundException;

    List<OrderResponse> getAll();

    public OrderResponse save(OrderRequest orderRequest) throws NotFoundException;

    OrderResponse update(UUID orderId, OrderRequest request) throws NotFoundException;

   // OrderResponse changeOrderStatus(UUID orderId, OrderStatus status) throws NotFoundException;
}
