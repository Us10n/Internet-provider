package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.BankAccountDao;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.entity.BankAccount;
import com.epamjwd.provider.model.service.BankAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class BankAccountServiceImpl implements BankAccountService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public BankAccount findBankAccountByUserId(long userId) throws ServiceException {
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
}
