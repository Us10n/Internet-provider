package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.BankAccount;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * The interface Bank account dao.
 */
public interface BankAccountDao extends Dao<BankAccount> {

    /**
     * Finds user by id.
     *
     * @param userId the user id
     * @return founded bankAccount
     * @throws DaoException the dao exception
     */
    Optional<BankAccount> findByUserId(long userId) throws DaoException;

    /**
     * Updates balance by bank account id with newBalance.
     *
     * @param bankAccountId the bank account id
     * @param newBalance    the new balance
     * @throws DaoException the dao exception
     */
    void updateBalance(long bankAccountId, BigDecimal newBalance) throws DaoException;

    /**
     * Update tariff id by bank account id with tariffId value
     *
     * @param bankAccountId the bank account id
     * @param tariffId      the tariff id
     * @throws DaoException the dao exception
     */
    void updateTariffId(long bankAccountId, Long tariffId) throws DaoException;

    /**
     * Unsubscribe bank account from deactivated tariff.
     *
     * @throws DaoException the dao exception
     */
    void deleteTariffIdWhereTariffIsDeactivated() throws DaoException;
}
