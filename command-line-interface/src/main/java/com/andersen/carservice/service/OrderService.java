package com.andersen.carservice.service;

import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.model.request.OrderRequest;
import com.andersen.carservice.model.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void deleteById(UUID orderId) throws NotFoundException;

    OrderResponse getById(UUID orderId) throws NotFoundException;

    List<OrderResponse> getAll();

    OrderResponse save(OrderRequest orderRequest) throws NotFoundException;

    OrderResponse update(UUID orderId, OrderRequest request) throws NotFoundException;
}
