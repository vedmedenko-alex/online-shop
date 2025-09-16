package com.solvd.online_shop.services.interfaces;

import java.sql.SQLException;

import java.util.List;

public interface IGenericService<T> {

    void add(T entity);

    T getById(int id);

    List<T> getAll();

    void update(T entity);

    void delete(int id);
}
