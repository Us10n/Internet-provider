package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.BankAccountDao;
import com.epamjwd.provider.model.dao.Dao;
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
    public boolean rechargeBalance(BankAccount bankAccount, String rechargeAmount) throws ServiceException {
        if (bankAccount == null) {
            logger.error("Bank account can't be null");
            throw new ServiceException("Bank account can't be null");
        }
        if (!isRechargeAmountValid(rechargeAmount)) {
            return false;
        }
        BankAccountDao bankAccountDao = DaoHolder.getInstance().getBankAccountDao();

        try {
            BigDecimal newBalance = new BigDecimal(rechargeAmount).add(bankAccount.getBalance());
            bankAccountDao.updateBalance(bankAccount.getId(), newBalance);
            bankAccount.setBalance(newBalance);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return false;
        }
        return true;
    }

    private boolean isRechargeAmountValid(String rechargeAmount) {
        BankAccountValidator bankAccountValidator = BankAccountValidatorImpl.getInstance();
        return bankAccountValidator.isRequestedMoneyAmountValid(rechargeAmount);
    }
}
