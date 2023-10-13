package com.andersen.carservice.storage.impl;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.storage.RepairerStorage;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class RepairerStorageImpl implements RepairerStorage {

    private final Map<UUID, Repairer> repairers;

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
