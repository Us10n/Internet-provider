package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.dao.SpecialOfferDao;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.service.SpecialOfferService;
import com.epamjwd.provider.model.service.validator.impl.SpecialOfferValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecialOfferServiceImpl implements SpecialOfferService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<SpecialOffer> findAllPromotions() throws ServiceException {
        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        List<SpecialOffer> specialOfferList = new ArrayList<>();
        try {
            specialOfferList = specialOfferDao.findAll();
        } catch (DaoException e) {
            logger.error("Special offers find all error", e);
            throw new ServiceException("Special offers find all error", e);
        }

        return specialOfferList;
    }

    @Override
    public Optional<SpecialOffer> findByTitle(String title) throws ServiceException {
        if (!SpecialOfferValidatorImpl.getInstance().isTitleValid(title)) {
            return Optional.empty();
        }

        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        Optional<SpecialOffer> specialOffer;
        try {
            specialOffer = specialOfferDao.findByTitle(title);
        } catch (DaoException e) {
            logger.error("Special offers find by name error", e);
            throw new ServiceException("Special offers find by name error", e);
        }

        return specialOffer;
    }
}
