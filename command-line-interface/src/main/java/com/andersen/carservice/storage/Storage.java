package com.andersen.carservice.storage;

import java.util.Optional;

public interface Storage<T, K> {

    T save(T object);

    void deleteById(K id);

    Optional<T> findById(K id);
}
