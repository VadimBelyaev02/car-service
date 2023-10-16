package com.andersen.carservice.storage;

import java.util.List;
import java.util.Optional;

public interface Storage<T, K> {

    List<T> findAll();
    T save(T object);

    void deleteById(K id);

    Optional<T> findById(K id);
}
