package com.epamjwd.provider.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> findTariffsAndSortByName() throws ServiceException;
    List<Tariff> findTariffsAndSortByPrice() throws ServiceException;
    List<Tariff> findTariffsAndSortBySpeed() throws ServiceException;
    List<Tariff> findTariffsAndSortByRating() throws ServiceException;
}
