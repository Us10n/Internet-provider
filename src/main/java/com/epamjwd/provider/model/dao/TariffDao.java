package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Tariff;

import java.util.List;
import java.util.Optional;

/**
 * The interface Tariff dao.
 */
public interface TariffDao extends Dao<Tariff> {

    /**
     * Finds tariff by tariff name.
     *
     * @param tariffName the tariff name
     * @return founded tariff
     * @throws DaoException the dao exception
     */
    Optional<Tariff> findByName(String tariffName) throws DaoException;

    /**
     * Finds tariff list sorted by name.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tariff> findAllSortByName() throws DaoException;

    /**
     * Finds tariff list sorted by internet speed.
     *
     * @return tariff list
     * @throws DaoException the dao exception
     */
    List<Tariff> findAllSortByInternetSpeed() throws DaoException;

    /**
     * Finds tariff list sorted by price.
     *
     * @return tariff list
     * @throws DaoException the dao exception
     */
    List<Tariff> findAllSortByPrice() throws DaoException;

    /**
     * Finds tariff list sorted by rating
     *
     * @return tariff list
     * @throws DaoException the dao exception
     */
    List<Tariff> findAllSortByRating() throws DaoException;

    /**
     * Updates tariff by tariff name.
     *
     * @param tariffName the tariff name
     * @param newTariff  the tariff new data
     * @throws DaoException the dao exception
     */
    void updateByName(String tariffName, Tariff newTariff) throws DaoException;

    /**
     * Delete special offer by id.
     *
     * @param specialOfferId the special offer id
     * @throws DaoException the dao exception
     */
    void deleteSpecialOfferId(long specialOfferId) throws DaoException;

    /**
     * Updates tariff rating.
     *
     * @throws DaoException the dao exception
     */
    void updateTariffRating() throws DaoException;

}
