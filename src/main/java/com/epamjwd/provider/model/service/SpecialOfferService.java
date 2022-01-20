package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.util.List;

public interface SpecialOfferService {
    List<SpecialOffer> findAllPromotions() throws ServiceException;
}
