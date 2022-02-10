package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Feedback;

import java.util.List;

/**
 * The interface Feedback dao.
 */
public interface FeedbackDao extends Dao<Feedback> {

    /**
     * Finds feedback by tariff id.
     *
     * @param tariffId the tariff id
     * @return feedback list.
     * @throws DaoException the dao exception
     */
    List<Feedback> findByTariffId(long tariffId) throws DaoException;
}
