package com.andersen.carservice.service;

import com.andersen.carservice.entity.Order;
import com.andersen.carservice.entity.enums.OrderStatus;
import com.andersen.carservice.exception.NotFoundException;
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

    public void assignRepairers(UUID orderId, List<UUID> repairersIds) throws NotFoundException {
//        Optional<Order> orderOptional = orderStorage.findById(orderId);
//        if (orderOptional.isPresent()) {
//            Order order = orderOptional.get();
//            for (UUID repairerId: repairersIds){
//                Optional<Repairer> repairerOptional = repairerStorage.findById(repairerId);
//                if (repairerOptional.isPresent()) {
//                    order
//                } else {
//
//                }
//            }
//        }

        boolean success = orderStorage.findById(orderId).map(order -> {
            repairersIds.forEach(repairerId -> {
                repairerStorage.findById(repairerId).ifPresent(repairer -> {
                    order.addRepairer(repairerId);
                    repairer.addOrder(orderId);
                });
            });
            return true;
        }).orElse(false);

        if (!success) {
            throw new NotFoundException(OrderUtil.notFoundById(orderId));
        }
    }

    @Override
    public void deleteOrder(UUID orderId) throws NotFoundException {
        boolean success = orderStorage.findById(orderId).map(
                order -> {
                    order.getRepairersIds().forEach(repairerId -> {
                        repairerStorage.findById(repairerId).ifPresent(
                                repairer -> repairer.deleteOrder(orderId)
                        );
                    });
                    orderStorage.deleteById(orderId);
                    return true;
                }
        ).orElse(false);
        if (!success) {
            throw new NotFoundException(OrderUtil.notFoundById(orderId));
        }
    }


    @Override
    public Order getById(UUID orderId) throws NotFoundException {
        return orderStorage.findById(orderId).orElseThrow(
                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
        );
    }

    @Override
    public List<Order> getAll() {
        return orderStorage.findAll();
    }


    @Override
    public void save(Order order) {
        order.setId(UuidHelper.generate());
        order.setStatus(OrderStatus.ACTIVE);
        order.setOpeningDate(Instant.now());
        orderStorage.save(order);
    }

    @Override
    public void changeOrderStatus(UUID orderId, OrderStatus status) throws NotFoundException {
        Order order = orderStorage.findById(orderId).orElseThrow(
                () -> new NotFoundException(OrderUtil.notFoundById(orderId))
        );
        order.setStatus(status);
        orderStorage.save(order);
    }

    public void deleteById(UUID orderId) throws NotFoundException {
        Order order = getById(orderId);
        order.getRepairersIds().forEach(
                repairerStorage::deleteById
        );
        orderStorage.deleteById(orderId);
    }
}
