package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffDao extends Dao<Tariff> {

    List<Tariff> findByName(String tariffName) throws DaoException;
}
