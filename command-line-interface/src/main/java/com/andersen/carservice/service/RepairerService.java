package com.andersen.carservice.service;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.response.RepairerResponse;

import java.util.List;
import java.util.UUID;

public interface RepairerService {
    RepairerResponse getById(UUID repairerId) throws NotFoundException;

    void delete(UUID repairerId) throws NotFoundException;

    RepairerResponse save(Repairer repairer);

    List<RepairerResponse> getAll();
}
