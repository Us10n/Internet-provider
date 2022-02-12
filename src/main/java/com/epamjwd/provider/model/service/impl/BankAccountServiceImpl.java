package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.BankAccountDao;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.entity.BankAccount;
import com.epamjwd.provider.model.service.BankAccountService;
import com.epamjwd.provider.model.service.validator.BankAccountValidator;
import com.epamjwd.provider.model.service.validator.impl.BankAccountValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public class BankAccountServiceImpl implements BankAccountService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public BankAccount findBankAccountByUserId(Long userId) throws ServiceException {
        if (userId == null) {
            logger.error("User id can't be null");
            throw new ServiceException("User id can't be null");
        }
        BankAccountDao bankAccountDao = DaoHolder.getInstance().getBankAccountDao();
        Optional<BankAccount> bankAccount;
        try {
            bankAccount = bankAccountDao.findByUserId(userId);
        } catch (DaoException e) {
            logger.error("BankAccount find error", e);
            throw new ServiceException("BankAccount find error", e);
        }
        if (bankAccount.isEmpty()) {
            logger.error("BankAccount didn't found");
            throw new ServiceException("BankAccount didn't found");
        }
        return bankAccount.get();
    }

    @Override
    public boolean rechargeBalance(Long userId, String rechargeAmount) throws ServiceException {
        if (userId == null) {
            logger.error("User id can't be null");
            throw new ServiceException("User id can't be null");
        }
        if (!isRechargeAmountValid(rechargeAmount)) {
            return false;
        }
        BankAccountDao bankAccountDao = DaoHolder.getInstance().getBankAccountDao();
        try {
            Optional<BankAccount> bankAccountOptional = bankAccountDao.findByUserId(userId);
            if (bankAccountOptional.isEmpty()) {
                return false;
            }
            BankAccount bankAccount = bankAccountOptional.get();
            BigDecimal oldBalance = bankAccount.getBalance();
            BigDecimal newBalance = new BigDecimal(rechargeAmount).add(oldBalance);
            bankAccountDao.updateBalance(bankAccount.getId(), newBalance);
        } catch (DaoException e) {
            logger.error("BankAccount update balance error", e);
            throw new ServiceException("BankAccount update balance error", e);
        }
        return true;
    }

    @Override
    public boolean subscribeTariff(Long userId, String tariffId) throws ServiceException {
        if (userId == null || tariffId == null) {
            return false;
        }
        BankAccountDao bankAccountDao = DaoHolder.getInstance().getBankAccountDao();
        try {
            Optional<BankAccount> optionalBankAccount = bankAccountDao.findByUserId(userId);
            if (optionalBankAccount.isEmpty() || optionalBankAccount.get().getBalance().compareTo(BigDecimal.ZERO) < 0) {
                return false;
            }
            long bankAccountId = optionalBankAccount.get().getId();
            long tariffIdLong = Long.parseLong(tariffId);
            bankAccountDao.updateTariffIdByBankAccountId(bankAccountId, tariffIdLong);
        } catch (DaoException e) {
            logger.error("BankAccount update tariff id error", e);
            throw new ServiceException("BankAccount update tariff id error", e);
        }
        return true;
    }

    @Override
    public boolean unsubscribeTariff(Long userId) throws ServiceException {
        if (userId == null) {
            return false;
        }
        BankAccountDao bankAccountDao = DaoHolder.getInstance().getBankAccountDao();
        try {
            Optional<BankAccount> optionalBankAccount = bankAccountDao.findByUserId(userId);
            if (optionalBankAccount.isEmpty()) {
                return false;
            }
            long bankAccountId = optionalBankAccount.get().getId();
            bankAccountDao.updateTariffIdByBankAccountId(bankAccountId, null);
        } catch (DaoException e) {
            logger.error("BankAccount update tariff id error", e);
            throw new ServiceException("BankAccount update tariff id error", e);
        }
        return true;
    }

    private boolean isRechargeAmountValid(String rechargeAmount) {
        BankAccountValidator bankAccountValidator = BankAccountValidatorImpl.getInstance();
        return bankAccountValidator.isRequestedMoneyAmountValid(rechargeAmount);
    }
}
