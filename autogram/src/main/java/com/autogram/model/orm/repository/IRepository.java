package com.autogram.model.orm.repository;

import com.autogram.model.orm.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    T create(T model);

    T update(T model);

    void delete(T model);

    Optional<List<T>> query(Specification specification);
}
