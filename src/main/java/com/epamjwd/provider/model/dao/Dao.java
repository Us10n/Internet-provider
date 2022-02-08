package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Identifiable;

import java.util.List;
import java.util.Optional;

/**
 * The interface Dao.
 *
 * @param <T> indicates belonging to Identifiable interface.
 */
public interface Dao<T extends Identifiable> {

    /**
     * Finds all instances of class T in database query.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * Finds instance of class T by id.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Creates new instance of T class.
     *
     * @param entity the entity
     * @return the long
     * @throws DaoException the dao exception
     */
    long create(T entity) throws DaoException;
}
