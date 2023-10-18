package com.andersen.carservice.storage.impl;

import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor()
public class RepairerStorageImpl implements RepairerStorage {

    private final Map<UUID, Repairer> repairers = new HashMap<>();

    @Override
    public List<Repairer> findAll() {
        return repairers.values().stream().toList();
    }

    @Override
    public Repairer save(Repairer repairer) {
        if (Objects.isNull(repairer.getId())) { // check not null repairer
            repairer.setId(UuidHelper.generate());
        }
        repairers.put(repairer.getId(), repairer);
        return repairer;
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

    @Override
    public boolean existsByEmail(String email) {
        return repairers.values().stream()
                .anyMatch(repairer -> Objects.equals(email, repairer.getEmail()));
    }
}
