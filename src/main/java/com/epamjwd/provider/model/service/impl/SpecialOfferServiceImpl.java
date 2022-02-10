package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.dao.SpecialOfferDao;
import com.epamjwd.provider.model.dao.TariffDao;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.service.SpecialOfferService;
import com.epamjwd.provider.model.service.validator.SpecialOfferValidator;
import com.epamjwd.provider.model.service.validator.impl.SpecialOfferValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SpecialOfferServiceImpl implements SpecialOfferService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<SpecialOffer> findAllPromotions() throws ServiceException {
        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        try {
            List<SpecialOffer> specialOfferList;
            specialOfferList = specialOfferDao.findAll();
            return specialOfferList;
        } catch (DaoException e) {
            logger.error("Special offers find all error", e);
            throw new ServiceException("Special offers find all error", e);
        }
    }

    @Override
    public Optional<SpecialOffer> findById(String offerId) throws ServiceException {
        if (offerId == null) {
            return Optional.empty();
        }

        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        try {
            Optional<SpecialOffer> specialOffer;
            long offerIdLong = Long.parseLong(offerId);
            specialOffer = specialOfferDao.findById(offerIdLong);
            return specialOffer;
        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return Optional.empty();
        } catch (DaoException e) {
            logger.error("Special offers find by name error", e);
            throw new ServiceException("Special offers find by name error", e);
        }
    }

    @Override
    public Optional<SpecialOffer> findByTitle(String title) throws ServiceException {
        if (!SpecialOfferValidatorImpl.getInstance().isTitleValid(title)) {
            return Optional.empty();
        }

        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        try {
            Optional<SpecialOffer> specialOffer;
            specialOffer = specialOfferDao.findByTitle(title);
            return specialOffer;
        } catch (DaoException e) {
            logger.error("Special offers find by name error", e);
            throw new ServiceException("Special offers find by name error", e);
        }
    }

    @Override
    public boolean deleteSpecialOfferById(String offerId) throws ServiceException {
        if (offerId == null) {
            return false;
        }

        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            long offerIdLong = Long.parseLong(offerId);
            if (specialOfferDao.findById(offerIdLong).isEmpty()) {
                return false;
            }
            tariffDao.deleteSpecialOfferId(offerIdLong);
            specialOfferDao.deleteById(offerIdLong);
        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return false;
        } catch (DaoException e) {
            logger.error("Special offer delete error", e);
            throw new ServiceException("Special offer delete error", e);
        }
        return true;
    }

    @Override
    public boolean updateSpecialOfferByTitle(String title, String startDateString,
                                             String expirationDateString, String discount,
                                             String image, String description) throws ServiceException {
        if (!isSpecialOfferFormValid(title, startDateString, expirationDateString, discount, image, description)) {
            return false;
        }
        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        try {
            if (specialOfferDao.findByTitle(title).isEmpty()) {
                return false;
            }
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
            Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDateString);
            byte discountByte = Byte.parseByte(discount);

            SpecialOffer newSpecialOffer = new SpecialOffer(description, discountByte, startDate, expirationDate, image);
            specialOfferDao.updateByTitle(title, newSpecialOffer);
        } catch (ParseException e) {
            logger.error("Date parse error", e);
            throw new ServiceException("Date parse error", e);
        } catch (DaoException e) {
            logger.error("Special offer update error", e);
            throw new ServiceException("Special offer update error", e);
        }
        return true;
    }

    @Override
    public boolean createSpecialOffer(String title, String startDateString, String expirationDateString,
                                      String discount, String image, String description) throws ServiceException {
        if (!isSpecialOfferFormValid(title, startDateString, expirationDateString, discount, image, description)) {
            return false;
        }
        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        try {
            if (specialOfferDao.findByTitle(title).isPresent()) {
                return false;
            }
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
            Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDateString);
            byte discountByte = Byte.parseByte(discount);

            SpecialOffer newSpecialOffer = new SpecialOffer(title, description, discountByte, startDate, expirationDate, image);
            specialOfferDao.create(newSpecialOffer);
        } catch (DaoException e) {
            logger.error("Special offer create error", e);
            throw new ServiceException("Special offer create error", e);
        } catch (ParseException e) {
            logger.error("Date parse error", e);
            throw new ServiceException("Date parse error", e);
        }
        return true;
    }

    private boolean isSpecialOfferFormValid(String title, String startDate, String expirationDate, String discount, String image, String description) {
        SpecialOfferValidator specialOfferValidator = SpecialOfferValidatorImpl.getInstance();
        return specialOfferValidator.isTitleValid(title) && specialOfferValidator.isDateValid(startDate) &&
                specialOfferValidator.isDateValid(expirationDate) && specialOfferValidator.isDiscountValid(discount) &&
                specialOfferValidator.isImageNameValid(image) && specialOfferValidator.isDescriptionValid(description);
    }
}
