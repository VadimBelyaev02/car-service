package com.andersen.carservice.service;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.entity.enums.RepairerStatus;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;
import com.andersen.carservice.util.constants.RepairerUtil;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class RepairerService {

    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;

    public Repairer getById(UUID repairerId) throws NotFoundException {
        return repairerStorage.findById(repairerId).orElseThrow(
                () -> new NotFoundException(RepairerUtil.notFoundById(repairerId))
        );
    }

    public void delete(UUID repairerId) throws NotFoundException {
        Repairer repairer = getById(repairerId);
        repairer.getOrdersIds().forEach(
                orderStorage::deleteById
        );
        repairerStorage.deleteById(repairerId);
    }

    public void save(Repairer repairer) {
        repairer.setId(UuidHelper.generate());
        repairer.setStatus(RepairerStatus.ACTIVE);
        repairerStorage.save(repairer);
    }

    public List<Repairer> getAll() {
        return repairerStorage.findAll();
    }

}
