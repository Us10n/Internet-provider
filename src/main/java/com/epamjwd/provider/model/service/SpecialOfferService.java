package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.util.List;
import java.util.Optional;

/**
 * The interface Special offer service.
 */
public interface SpecialOfferService {
    /**
     * Find all promotions list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<SpecialOffer> findAllPromotions() throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<SpecialOffer> findById(String id) throws ServiceException;

    /**
     * Find by title optional.
     *
     * @param title the title
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<SpecialOffer> findByTitle(String title) throws ServiceException;

    /**
     * Delete special offer by id boolean.
     *
     * @param offerId the offer id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteSpecialOfferById(String offerId) throws ServiceException;

    /**
     * Update special offer by title boolean.
     *
     * @param title          the title
     * @param startDate      the start date
     * @param expirationDate the expiration date
     * @param discount       the discount
     * @param image          the image
     * @param description    the description
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateSpecialOfferByTitle(String title, String startDate, String expirationDate,
                                      String discount, String image, String description) throws ServiceException;

    /**
     * Create special offer boolean.
     *
     * @param title          the title
     * @param startDate      the start date
     * @param expirationDate the expiration date
     * @param discount       the discount
     * @param image          the image
     * @param description    the description
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createSpecialOffer(String title, String startDate, String expirationDate,
                               String discount, String image, String description) throws ServiceException;
}
