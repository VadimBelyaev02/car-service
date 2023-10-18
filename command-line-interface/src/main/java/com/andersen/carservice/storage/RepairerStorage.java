package com.andersen.carservice.storage;

import com.andersen.carservice.entity.Repairer;

import java.util.UUID;

public interface RepairerStorage extends Storage<Repairer, UUID> {

    boolean existsById(UUID id);

    boolean existsByEmail(String email);
}
