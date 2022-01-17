package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    List<T> findAll() throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    long create(T entity) throws DaoException;
}
