package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.BankAccountDao;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.dao.SpecialOfferDao;
import com.epamjwd.provider.model.dao.TariffDao;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.entity.TariffStatus;
import com.epamjwd.provider.model.entity.comparator.TariffNewPriceComparator;
import com.epamjwd.provider.model.service.TariffService;
import com.epamjwd.provider.model.service.validator.TariffValidator;
import com.epamjwd.provider.model.service.validator.impl.TariffValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TariffServiceImpl implements TariffService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Tariff> findTariffsAndSortByName() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            List<Tariff> tariffList;
            tariffList = tariffDao.findAllSortByName();
            countPriceAfterDiscountList(tariffList);
            return tariffList;
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

    }

    @Override
    public List<Tariff> findTariffsAndSortByPrice() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            List<Tariff> tariffList;
            tariffList = tariffDao.findAllSortByPrice();
            countPriceAfterDiscountList(tariffList);
            tariffList = sortTariffsByPrice(tariffList);
            return tariffList;
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

    }

    @Override
    public List<Tariff> findTariffsAndSortBySpeed() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            List<Tariff> tariffList;
            tariffList = tariffDao.findAllSortByInternetSpeed();
            countPriceAfterDiscountList(tariffList);
            return tariffList;
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

    }

    @Override
    public List<Tariff> findTariffsAndSortByRating() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            List<Tariff> tariffList;
            tariffList = tariffDao.findAllSortByRating();
            countPriceAfterDiscountList(tariffList);
            return tariffList;
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

    }

    @Override
    public Optional<Tariff> findTariffByName(String tariffName) throws ServiceException {
        if (!TariffValidatorImpl.getInstance().isNameValid(tariffName)) {
            return Optional.empty();
        }
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            Optional<Tariff> optionalTariff;
            optionalTariff = tariffDao.findByName(tariffName);
            optionalTariff.ifPresent(this::countPriceAfterDiscountSingle);
            return optionalTariff;
        } catch (DaoException e) {
            logger.error("Tariff find by name error", e);
            throw new ServiceException("Tariff find by name error", e);
        }
    }

    @Override
    public Optional<Tariff> findTariffById(long id) throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            Optional<Tariff> optionalTariff;
            optionalTariff = tariffDao.findById(id);
            optionalTariff.ifPresent(this::countPriceAfterDiscountSingle);
            return optionalTariff;
        } catch (DaoException e) {
            logger.error("Tariff find by id error", e);
            throw new ServiceException("Tariff find by id error", e);
        }
    }

    @Override
    public Optional<Tariff> findTariffById(String id) throws ServiceException {
        if (id == null) {
            return Optional.empty();
        }
        try {
            long tariffIdLong = Long.parseLong(id);
            return findTariffById(tariffIdLong);
        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return Optional.empty();
        }
    }

    @Override
    public boolean createNewTariff(String name, String internetSpeed, String price, String image, String description) throws ServiceException {
        if (!isTariffFormValid(name, internetSpeed, price, image, description)) {
            return false;
        }
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            if (tariffDao.findByName(name).isPresent()) {
                return false;
            }
            BigDecimal priceDecimal = new BigDecimal(price);
            Long internetSpeedLong = Long.valueOf(internetSpeed);
            Tariff newTariff = new Tariff(name, description, priceDecimal, internetSpeedLong, image);
            tariffDao.create(newTariff);
        } catch (DaoException e) {
            logger.error("Tariff create error", e);
            throw new ServiceException("Tariff create error", e);
        }
        return true;
    }

    @Override
    public boolean updateTariff(String name, String newInternetSpeed,
                                String newPrice, String newImage,
                                String newDescription, String newStatus,
                                String newSpecialOfferTitle) throws ServiceException {
        if (!isTariffFormValid(name, newInternetSpeed, newPrice, newImage, newDescription, newStatus)) {
            return false;
        }
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        SpecialOfferDao specialOfferDao = DaoHolder.getInstance().getSpecialOfferDao();
        BankAccountDao bankAccountDao = DaoHolder.getInstance().getBankAccountDao();
        try {
            if (tariffDao.findByName(name).isEmpty()) {
                return false;
            }
            Optional<SpecialOffer> specialOfferOptional = specialOfferDao.findByTitle(newSpecialOfferTitle);
            if (newSpecialOfferTitle != null && specialOfferOptional.isEmpty()) {
                return false;
            }
            BigDecimal priceDecimal = new BigDecimal(newPrice);
            Long internetSpeedLong = Long.valueOf(newInternetSpeed);
            TariffStatus tariffStatus = TariffStatus.valueOf(newStatus);
            Tariff newTariff = new Tariff(internetSpeedLong, priceDecimal, newImage, newDescription, tariffStatus, specialOfferOptional);
            tariffDao.updateByName(name, newTariff);

            bankAccountDao.deleteTariffIdWhereTariffIsDeactivated();
        } catch (DaoException e) {
            logger.error("Tariff update error", e);
            throw new ServiceException("Tariff update error", e);
        }
        return true;
    }

    private boolean isTariffFormValid(String name, String internetSpeed,
                                      String price, String image,
                                      String description) {
        TariffValidator tariffValidator = TariffValidatorImpl.getInstance();
        return tariffValidator.isNameValid(name) && tariffValidator.isInternetSpeedValid(internetSpeed) &&
                tariffValidator.isPriceValid(price) && tariffValidator.isImageNameValid(image) &&
                tariffValidator.isDescriptionValid(description);
    }

    private boolean isTariffFormValid(String name, String internetSpeed, String price,
                                      String image, String description, String status) {
        TariffValidator tariffValidator = TariffValidatorImpl.getInstance();
        return tariffValidator.isNameValid(name) && tariffValidator.isInternetSpeedValid(internetSpeed) &&
                tariffValidator.isPriceValid(price) && tariffValidator.isImageNameValid(image) &&
                tariffValidator.isDescriptionValid(description) && tariffValidator.isTariffStatusValid(status);
    }

    private void countPriceAfterDiscountList(List<Tariff> tariffList) {
        for (Tariff tariff : tariffList) {
            countPriceAfterDiscountSingle(tariff);
        }
    }

    private void countPriceAfterDiscountSingle(Tariff tariff) {
        BigDecimal oneHundred = new BigDecimal(100);
        Optional<SpecialOffer> specialOffer = tariff.getSpecialOffer();
        if (specialOffer.isPresent() && isSpecialOfferValid(specialOffer.get())) {
            BigDecimal discount = new BigDecimal(specialOffer.get().getDiscount());
            BigDecimal newPrice = tariff.getPrice()
                    .multiply(oneHundred.subtract(discount))
                    .divide(oneHundred);
            tariff.setNewPrice(newPrice);
        }
    }

    private boolean isSpecialOfferValid(SpecialOffer specialOffer) {
        Date todayDate = new Date();
        Date specialOfferStartDate = specialOffer.getStartDate();
        Date specialOfferExpirationDate = specialOffer.getExpirationDate();
        return (todayDate.after(specialOfferStartDate) || todayDate.compareTo(specialOfferStartDate) == 0) &&
                (todayDate.before(specialOfferExpirationDate) || todayDate.compareTo(specialOfferExpirationDate) == 0);
    }

    private List<Tariff> sortTariffsByPrice(List<Tariff> tariffList) {
        return tariffList.stream().sorted(new TariffNewPriceComparator()).collect(Collectors.toList());
    }
}
