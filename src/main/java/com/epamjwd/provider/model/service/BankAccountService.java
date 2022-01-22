package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;

import java.util.Optional;

public interface BankAccountService {
    BankAccount findBankAccountByUserId(long userId) throws ServiceException;
}
