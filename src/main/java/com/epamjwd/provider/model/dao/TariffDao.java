package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.entity.TariffStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TariffDao extends Dao<Tariff> {

    Optional<Tariff> findByName(String tariffName) throws DaoException;

    List<Tariff> findAllSortByName() throws DaoException;

    List<Tariff> findAllSortByInternetSpeed() throws DaoException;

    List<Tariff> findAllSortByPrice() throws DaoException;

    List<Tariff> findAllSortByRating() throws DaoException;

    void updateByName(String tariffName, Tariff newTariff) throws DaoException;

    void deleteSpecialOfferId(long specialOfferId) throws DaoException;
}
