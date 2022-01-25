package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.util.Optional;

public interface SpecialOfferDao extends Dao<SpecialOffer> {
    Optional<SpecialOffer> findByTitle(String title) throws DaoException;

    void deleteById(long id) throws DaoException;
    void updateByTitle(String title, SpecialOffer specialOffer) throws DaoException;
}
