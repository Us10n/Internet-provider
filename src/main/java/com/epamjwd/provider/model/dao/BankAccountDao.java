package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.BankAccount;

import java.util.Optional;

public interface BankAccountDao extends Dao<BankAccount>{

    Optional<BankAccount> findByUserId(long userId) throws DaoException;
}
