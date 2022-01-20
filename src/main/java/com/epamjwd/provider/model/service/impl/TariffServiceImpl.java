package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.dao.TariffDao;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.entity.comparator.TariffNewPriceComparator;
import com.epamjwd.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class TariffServiceImpl implements TariffService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Tariff> findTariffsAndSortByName() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        List<Tariff> tariffList = new ArrayList<>();
        try {
            tariffList = tariffDao.findAllSortByName();
            countPriceAfterDiscountList(tariffList);
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

        return tariffList;
    }

    @Override
    public List<Tariff> findTariffsAndSortByPrice() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        List<Tariff> tariffList = new ArrayList<>();
        try {
            tariffList = tariffDao.findAllSortByPrice();
            countPriceAfterDiscountList(tariffList);
            tariffList = sortTariffsByPrice(tariffList);
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

        return tariffList;
    }

    @Override
    public List<Tariff> findTariffsAndSortBySpeed() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        List<Tariff> tariffList = new ArrayList<>();
        try {
            tariffList = tariffDao.findAllSortByInternetSpeed();
            countPriceAfterDiscountList(tariffList);
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

        return tariffList;
    }

    @Override
    public List<Tariff> findTariffsAndSortByRating() throws ServiceException {
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        List<Tariff> tariffList = new ArrayList<>();
        try {
            tariffList = tariffDao.findAllSortByRating();
            countPriceAfterDiscountList(tariffList);
        } catch (DaoException e) {
            logger.error("Tariffs find error", e);
            throw new ServiceException("Tariffs find error", e);
        }

        return tariffList;
    }

    @Override
    public Optional<Tariff> findTariffByName(String tariffName) throws ServiceException {
        if (tariffName == null) {
            return Optional.empty();
        }
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        Optional<Tariff> optionalTariff = Optional.empty();
        try {
            optionalTariff = tariffDao.findByName(tariffName);
            optionalTariff.ifPresent(this::countPriceAfterDiscountSingle);
        } catch (DaoException e) {
            logger.error("Tariff find by name error", e);
            throw new ServiceException("Tariff find by name error", e);
        }
        return optionalTariff;
    }

    private void countPriceAfterDiscountList(List<Tariff> tariffList) {
        for (Tariff tariff : tariffList) {
            countPriceAfterDiscountSingle(tariff);
        }
    }

    private void countPriceAfterDiscountSingle(Tariff tariff) {
        Optional<SpecialOffer> specialOffer = tariff.getSpecialOffer();
        if (specialOffer.isPresent() && isSpecialOfferValid(specialOffer.get())) {
            BigDecimal discount = new BigDecimal(specialOffer.get().getDiscount());
            BigDecimal newPrice = tariff.getPrice()
                    .multiply(discount)
                    .divide(BigDecimal.valueOf(100));
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
