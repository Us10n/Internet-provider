package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;

/**
 * The interface Bank account service.
 */
public interface BankAccountService {
    /**
     * Find bank account by user id bank account.
     *
     * @param userId the user id
     * @return the bank account
     * @throws ServiceException the service exception
     */
    BankAccount findBankAccountByUserId(Long userId) throws ServiceException;

    /**
     * Recharge balance.
     *
     * @param userId         the user id
     * @param rechargeAmount the recharge amount
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean rechargeBalance(Long userId, String rechargeAmount) throws ServiceException;

    /**
     * Subscribe tariff.
     *
     * @param userId   the user id
     * @param tariffId the tariff id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean subscribeTariff(Long userId, String tariffId) throws ServiceException;

    /**
     * Unsubscribe tariff.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unsubscribeTariff(Long userId) throws ServiceException;
}
