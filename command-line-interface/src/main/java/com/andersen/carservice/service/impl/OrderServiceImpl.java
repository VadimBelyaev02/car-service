package com.andersen.carservice.service.impl;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.mapper.OrderMapper;
import com.andersen.carservice.request.OrderRequest;
import com.andersen.carservice.response.OrderResponse;
import com.andersen.carservice.service.OrderService;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;
import com.andersen.carservice.util.constants.OrderUtil;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;
    private final OrderMapper orderMapper;

//    public OrderResponse assignRepairers(UUID orderId, List<UUID> repairersIds) throws NotFoundException {
//        Order order = orderStorage.findById(orderId).orElseThrow(
//                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
//        );
//        order.getRepairersIds().forEach(repairerId -> repairerStorage.findById(repairerId).ifPresent(repairer -> {
//            order.addRepairer(repairerId);
//            repairer.addOrder(orderId);
//        }));
//        return orderMapper.toResponse(order);
//    }

    @Override
    public void deleteById(UUID orderId) throws NotFoundException {
        Order order = orderStorage.findById(orderId).orElseThrow(
                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
        );
        order.getRepairersIds().forEach(repairerId -> {
            repairerStorage.findById(repairerId).ifPresent(
                    repairer -> repairer.deleteOrder(orderId)
            );
        });
        orderStorage.deleteById(orderId);
    }


    @Override
    public OrderResponse getById(UUID orderId) throws NotFoundException {
        Order order = orderStorage.findById(orderId).orElseThrow(
                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
        );
        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderStorage.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }


    private void synchronizeRepairers(Order order) {

    }

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        order.setId(UuidHelper.generate());
        order.setStatus(OrderStatus.ACTIVE);
        order.setOpeningDate(Instant.now());
        synchronizeRepairers(order);
        Order savedOrder = orderStorage.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse update(UUID orderId, OrderRequest request) throws NotFoundException {
        Order order = orderStorage.findById(orderId).orElseThrow(
                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
        );
        orderMapper.updateEntity(order, request);
        Order updatedOrder = orderStorage.save(order);
        return orderMapper.toResponse(updatedOrder);
    }

//    @Override
//    public void changeOrderStatus(UUID orderId, OrderStatus status) throws NotFoundException {
//        Order order = orderStorage.findById(orderId).orElseThrow(
//                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
//        );
//        order.setStatus(status);
//        orderStorage.save(order);
//    }

}
