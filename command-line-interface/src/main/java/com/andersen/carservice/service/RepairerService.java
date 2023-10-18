package com.andersen.carservice.service;

import com.andersen.carservice.exception.AlreadyExistsException;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.model.request.RepairerRequest;
import com.andersen.carservice.model.response.RepairerResponse;

import java.util.List;
import java.util.UUID;

public interface RepairerService {

    RepairerResponse getById(UUID repairerId) throws NotFoundException;

    void deleteById(UUID repairerId) throws NotFoundException;

    RepairerResponse save(RepairerRequest repairerRequest) throws NotFoundException, AlreadyExistsException;

    List<RepairerResponse> getAll();
}
