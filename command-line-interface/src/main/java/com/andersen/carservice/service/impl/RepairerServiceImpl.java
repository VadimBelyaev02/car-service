package com.andersen.carservice.service.impl;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.entity.enums.RepairerStatus;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.mapper.RepairerMapper;
import com.andersen.carservice.response.RepairerResponse;
import com.andersen.carservice.service.RepairerService;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;
import com.andersen.carservice.util.constants.RepairerUtil;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RepairerServiceImpl implements RepairerService {

    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;
    private final RepairerMapper repairerMapper;

    public RepairerResponse getById(UUID repairerId) throws NotFoundException {
        Repairer repairer = repairerStorage.findById(repairerId).orElseThrow(
                () -> new NotFoundException(RepairerUtil.notFoundById(repairerId))
        );
        return repairerMapper.toResponse(repairer);
    }

    public void delete(UUID repairerId) throws NotFoundException {
        Repairer repairer = repairerStorage.findById(repairerId).orElseThrow(
                () -> new NotFoundException(RepairerUtil.notFoundById(repairerId))
        );
        repairer.getOrdersIds().forEach(
                orderStorage::deleteById
        );
        repairerStorage.deleteById(repairerId);
    }

    public RepairerResponse save(Repairer repairer) {
        repairer.setId(UuidHelper.generate());
        repairer.setStatus(RepairerStatus.ACTIVE);
        Repairer savedRepairer = repairerStorage.save(repairer);
        return repairerMapper.toResponse(savedRepairer);
    }


    public List<RepairerResponse> getAll() {
        return repairerStorage.findAll().stream()
                .map(repairerMapper::toResponse)
                .toList();
    }

}
