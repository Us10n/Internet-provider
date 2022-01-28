package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffService {
    List<Tariff> findTariffsAndSortByName() throws ServiceException;

    List<Tariff> findTariffsAndSortByPrice() throws ServiceException;

    List<Tariff> findTariffsAndSortBySpeed() throws ServiceException;

    List<Tariff> findTariffsAndSortByRating() throws ServiceException;

    Optional<Tariff> findTariffByName(String tariffName) throws ServiceException;

    Optional<Tariff> findTariffById(long id) throws ServiceException;

    Optional<Tariff> findTariffById(String id) throws ServiceException;

    boolean createNewTariff(String name, String internetSpeed, String price, String image, String description) throws ServiceException;

    boolean updateTariff(String name, String newInternetSpeed, String newPrice, String newImage, String newDescription, String newStatus, String newSpecialOfferTitle) throws ServiceException;
}
