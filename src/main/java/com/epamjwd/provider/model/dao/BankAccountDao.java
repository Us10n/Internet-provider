package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.BankAccount;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankAccountDao extends Dao<BankAccount> {

    Optional<BankAccount> findByUserId(long userId) throws DaoException;

    void updateBalance(long bankAccountId, BigDecimal newBalance) throws DaoException;

    void updateTariffId(long bankAccountId, Long tariffId) throws DaoException;

    void deleteTariffIdWhereTariffIsDeactivated() throws DaoException;
}
