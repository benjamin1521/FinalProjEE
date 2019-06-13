package ua.training.model.dao;

import ua.training.model.entities.Report;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(Report id);

    void close();
}
