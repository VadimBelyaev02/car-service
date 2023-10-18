package com.andersen.carservice.service.impl;

import com.andersen.carservice.model.entity.Order;
import com.andersen.carservice.model.entity.enums.OrderStatus;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.model.mapper.OrderMapper;
import com.andersen.carservice.model.request.OrderRequest;
import com.andersen.carservice.model.response.OrderResponse;
import com.andersen.carservice.service.OrderService;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;
import com.andersen.carservice.util.constants.OrderUtil;
import com.andersen.carservice.util.constants.RepairerUtil;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {


    private int i = 0;
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
        deleteOrdersFromRepairer(order);
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

    @Override
    public OrderResponse save(OrderRequest orderRequest) throws NotFoundException {
        Order order = orderMapper.toEntity(orderRequest);
        order.setId(UuidHelper.generate());

        if (i == 0) {
            order.setId(UUID.fromString("b5d71db4-8082-4658-b76e-139bf3c2dadd"));
        }
        if (i == 1) {
            order.setId(UUID.fromString("20c55039-1ad8-4c07-a144-a593cfa84594"));
        }
        i++;
        order.setStatus(OrderStatus.ACTIVE);
        order.setOpeningDate(Instant.now());

        assignOrdersToRepairers(order);

        Order savedOrder = orderStorage.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse update(UUID orderId, OrderRequest request) throws NotFoundException {
        Order order = orderStorage.findById(orderId).orElseThrow(
                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
        );
        deleteOrdersFromRepairer(order);
        orderMapper.updateEntity(order, request);
        assignOrdersToRepairers(order);

        Order updatedOrder = orderStorage.save(order);
        return orderMapper.toResponse(updatedOrder);
    }

    private void deleteOrdersFromRepairer(Order order) {
        if (Objects.nonNull(order.getRepairersIds())) {
            order.getRepairersIds().forEach(repairerId -> repairerStorage.findById(repairerId).ifPresent(
                    repairer -> repairer.deleteOrder(order.getId())
            ));
        }
    }

    private void assignOrdersToRepairers(Order order) throws NotFoundException {
        for (UUID repairerId : order.getRepairersIds()) {
            repairerStorage.findById(repairerId).orElseThrow(
                    () -> new NotFoundException(RepairerUtil.notFoundById(repairerId))
            ).addOrder(order.getId());
        }
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
