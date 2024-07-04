package ru.lbarbaris.dataservice.service;

import ru.lbarbaris.dataservice.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);

}
