package com.app.base.generic;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    Optional<T> addOrUpdate(T t);

    List<T> addOrUpdateMany(List<T> items);

    Optional<T> findById(Long id);

    List<T> findAll();

    List<T> findAllById(List<ID> ids);

    Optional<T> deleteById(ID id);

    List<T> deleteAllById(List<ID> ids);

    boolean deleteAll();
}
