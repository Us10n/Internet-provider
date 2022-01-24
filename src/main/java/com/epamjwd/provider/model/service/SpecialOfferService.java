package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.util.List;
import java.util.Optional;

public interface SpecialOfferService {
    List<SpecialOffer> findAllPromotions() throws ServiceException;
    Optional<SpecialOffer> findByTitle(String title) throws ServiceException;
}
