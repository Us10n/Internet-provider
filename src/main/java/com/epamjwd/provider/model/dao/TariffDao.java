package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.entity.TariffStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TariffDao extends Dao<Tariff> {

    Optional<Tariff> findByName(String tariff) throws DaoException;
    void updateSpecialOfferId(long tariffId,long specialOfferId) throws DaoException;
    void deleteSpecialOfferId(long tariffId) throws DaoException;
    void updateStatus(long tariffId, TariffStatus tariffStatus) throws DaoException;
    void updatePrice(long tariffId, BigDecimal newPrice) throws DaoException;
}
