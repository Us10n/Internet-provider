package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackDao extends Dao<Feedback> {

    List<Feedback> findByUserId(long userId) throws DaoException;

    List<Feedback> findByTariffId(long tariffId) throws DaoException;
}
