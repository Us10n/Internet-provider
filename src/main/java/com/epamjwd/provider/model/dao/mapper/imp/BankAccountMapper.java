package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.model.dao.mapper.ColumnName;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.entity.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccount> {
    @Override
    public BankAccount map(ResultSet resultSet) throws SQLException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(resultSet.getLong(ColumnName.ID));
        bankAccount.setBalance(resultSet.getBigDecimal(ColumnName.BANK_ACCOUNT_BALANCE));
        bankAccount.setUserId(resultSet.getLong(ColumnName.BANK_ACCOUNT_USER_ID));

        Long tariffId = resultSet.getLong(ColumnName.BANK_ACCOUNT_TARIFF_ID);
        bankAccount.setTariffId(tariffId == 0 ? null : tariffId);

        return bankAccount;
    }
}
