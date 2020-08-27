package com.epam.afshop.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {
    boolean create(T object);
    List<T> getAll() throws SQLException;
    T getById(long id);
    boolean update(T object);
    boolean delete(T object);
}
