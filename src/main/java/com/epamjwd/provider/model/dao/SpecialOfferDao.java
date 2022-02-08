package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.util.Optional;

/**
 * The interface Special offer dao.
 */
public interface SpecialOfferDao extends Dao<SpecialOffer> {
    /**
     * Finds special offer by title.
     *
     * @param title the title
     * @return founded special offer
     * @throws DaoException the dao exception
     */
    Optional<SpecialOffer> findByTitle(String title) throws DaoException;

    /**
     * Deletes special offer by id.
     *
     * @param id the id
     * @throws DaoException the dao exception
     */
    void deleteById(long id) throws DaoException;

    /**
     * Updates special offer by title.
     *
     * @param title        the title
     * @param specialOffer the special offer
     * @throws DaoException the dao exception
     */
    void updateByTitle(String title, SpecialOffer specialOffer) throws DaoException;
}
