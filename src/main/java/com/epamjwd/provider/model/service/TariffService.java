package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Tariff;

import java.util.List;
import java.util.Optional;

/**
 * The interface Tariff service.
 */
public interface TariffService {
    /**
     * Find tariffs and sort by name list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tariff> findTariffsAndSortByName() throws ServiceException;

    /**
     * Find tariffs and sort by price list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tariff> findTariffsAndSortByPrice() throws ServiceException;

    /**
     * Find tariffs and sort by speed list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tariff> findTariffsAndSortBySpeed() throws ServiceException;

    /**
     * Find tariffs and sort by rating list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Tariff> findTariffsAndSortByRating() throws ServiceException;

    /**
     * Find tariff by name optional.
     *
     * @param tariffName the tariff name
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Tariff> findTariffByName(String tariffName) throws ServiceException;

    /**
     * Find tariff by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Tariff> findTariffById(long id) throws ServiceException;

    /**
     * Find tariff by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Tariff> findTariffById(String id) throws ServiceException;

    /**
     * Create new tariff boolean.
     *
     * @param name          the name
     * @param internetSpeed the internet speed
     * @param price         the price
     * @param image         the image
     * @param description   the description
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createNewTariff(String name, String internetSpeed, String price, String image, String description) throws ServiceException;

    /**
     * Update tariff boolean.
     *
     * @param name                 the name
     * @param newInternetSpeed     the new internet speed
     * @param newPrice             the new price
     * @param newImage             the new image
     * @param newDescription       the new description
     * @param newStatus            the new status
     * @param newSpecialOfferTitle the new special offer title
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateTariff(String name, String newInternetSpeed, String newPrice, String newImage, String newDescription, String newStatus, String newSpecialOfferTitle) throws ServiceException;
}
