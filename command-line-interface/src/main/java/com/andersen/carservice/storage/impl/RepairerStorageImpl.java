package com.andersen.carservice.storage.impl;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepairerStorageImpl implements RepairerStorage {

    private final Map<UUID, Repairer> repairers = new HashMap<>();
    private static RepairerStorageImpl instance;

    public static RepairerStorageImpl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new RepairerStorageImpl();
        }
        return instance;
    }

    @Override
    public List<Repairer> findAll() {
        return repairers.values().stream().toList();
    }

    @Override
    public Repairer save(Repairer repairer) {
        if (Objects.isNull(repairer.getId())) { // check not null repairer
            repairer.setId(UuidHelper.generate());
        }
        return repairers.put(repairer.getId(), repairer);
    }

    @Override
    public void deleteById(UUID id) {
        repairers.remove(id);
    }

    @Override
    public Optional<Repairer> findById(UUID id) {
        return Optional.ofNullable(repairers.get(id));
    }


    @Override
    public boolean existsById(UUID id) {
        return findById(id).isPresent();
    }
}
