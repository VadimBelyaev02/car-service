package com.andersen.carservice.service.impl;

import com.andersen.carservice.exception.AlreadyExistsException;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.model.entity.Repairer;
import com.andersen.carservice.model.entity.enums.RepairerStatus;
import com.andersen.carservice.model.mapper.RepairerMapper;
import com.andersen.carservice.model.request.RepairerRequest;
import com.andersen.carservice.model.response.RepairerResponse;
import com.andersen.carservice.service.RepairerService;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidUtil;
import com.andersen.carservice.util.constants.OrderUtil;
import com.andersen.carservice.util.constants.RepairerUtil;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RepairerServiceImpl implements RepairerService {

    private int i = 0;
    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;
    private final RepairerMapper repairerMapper;

    public RepairerResponse getById(UUID repairerId) throws NotFoundException {
        Repairer repairer = repairerStorage.findById(repairerId).orElseThrow(
                () -> new NotFoundException(RepairerUtil.notFoundById(repairerId))
        );
        return repairerMapper.toResponse(repairer);
    }

    public void deleteById(UUID repairerId) throws NotFoundException {
        Repairer repairer = repairerStorage.findById(repairerId).orElseThrow(
                () -> new NotFoundException(RepairerUtil.notFoundById(repairerId))
        );
        deleteAssignedRepairers(repairer);
        repairerStorage.deleteById(repairerId);
    }

    public RepairerResponse save(RepairerRequest repairerRequest) throws NotFoundException, AlreadyExistsException {
        Repairer repairer = repairerMapper.toEntity(repairerRequest);
        repairer.setId(UuidUtil.generate());

        // the next several lines of code are for testing commands in 'script.txt'
        if (i == 0) {
            repairer.setId(UUID.fromString("f0336cd8-6ef8-4a9c-8e84-997eaa7d7822"));
        }
        if (i == 1) {
            repairer.setId(UUID.fromString("a6e2c660-3424-4d7e-8e2f-1f90866964ff"));
        }
        if (i == 2) {
            repairer.setId(UUID.fromString("22d6c085-2754-41c1-a5cc-5f75169d0d38"));
        }
        i++;


        if (repairerStorage.existsByEmail(repairer.getEmail())) {
            throw new AlreadyExistsException(RepairerUtil.alreadyExistsByEmail(repairer.getEmail()));
        }

        repairer.setStatus(RepairerStatus.ACTIVE);
        assignRepairersToOrders(repairer);

        Repairer savedRepairer = repairerStorage.save(repairer);
        return repairerMapper.toResponse(savedRepairer);
    }

    private void deleteAssignedRepairers(Repairer repairer) {
        repairer.getOrdersIds().forEach(orderId ->
                orderStorage.findById(orderId).ifPresent(
                        order -> order.deleteRepairer(repairer.getId())
                )
        );
    }

    private void assignRepairersToOrders(Repairer repairer) throws NotFoundException {
        for (UUID orderId : repairer.getOrdersIds()) {
            orderStorage.findById(orderId).orElseThrow(
                    () -> new NotFoundException(OrderUtil.notFoundById(orderId))
            ).addRepairer(repairer.getId());
        }
    }

    public List<RepairerResponse> getAll() {
        return repairerStorage.findAll().stream()
                .map(repairerMapper::toResponse)
                .toList();
    }

}
