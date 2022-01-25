package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.util.List;
import java.util.Optional;

public interface SpecialOfferService {
    List<SpecialOffer> findAllPromotions() throws ServiceException;

    Optional<SpecialOffer> findByTitle(String title) throws ServiceException;

    boolean deleteSpecialOfferById(String offerId) throws ServiceException;

    boolean updateSpecialOfferByTitle(String title, String startDate, String expirationDate,
                                      String discount, String image, String description) throws ServiceException;

    boolean createSpecialOffer(String title, String startDate, String expirationDate,
                               String discount, String image, String description) throws ServiceException;
}
