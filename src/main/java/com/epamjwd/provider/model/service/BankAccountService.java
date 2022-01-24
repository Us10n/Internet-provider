package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;

public interface BankAccountService {
    BankAccount findBankAccountByUserId(Long userId) throws ServiceException;
    boolean rechargeBalance(BankAccount bankAccount ,String rechargeAmount) throws ServiceException;
}
