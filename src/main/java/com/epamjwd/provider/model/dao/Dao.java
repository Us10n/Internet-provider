package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> findAll() throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    void removeById(long id) throws DaoException;

    long create(T entity) throws DaoException;
}
