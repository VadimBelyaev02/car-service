package com.andersen.carservice.storage.impl;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.storage.RepairerStorage;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RepairerStorageImpl implements RepairerStorage {

    private final Map<Repairer, UUID> repairers;

    public RepairerStorageImpl(Map<Repairer, UUID> repairers) {
        this.repairers = repairers;
    }

    @Override
    public Repairer save(Repairer repairer) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public Optional<Repairer> findById(UUID id) {
        return Optional.empty();
    }
}
