package com.andersen.carservice.storage;

import com.andersen.carservice.model.entity.Repairer;

import java.util.UUID;

public interface RepairerStorage extends Storage<Repairer, UUID> {

    boolean existsByEmail(String email);
}
