package com.epamjwd.provider.model.dao.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.AbstractQueryExecutor;
import com.epamjwd.provider.model.dao.BankAccountDao;
import com.epamjwd.provider.model.dao.mapper.RowMapperHolder;
import com.epamjwd.provider.model.entity.BankAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class BankAccountDaoImpl extends AbstractQueryExecutor<BankAccount> implements BankAccountDao {

    private static final String FIND_ALL_BANKACCOUNT_QUERY = """
            SELECT bankaccounts.id, bankaccounts.users_id, bankaccounts.tariffs_id, bankaccounts.balance
            FROM internetprovider.bankaccounts""";
    private static final String FIND_BANKACCOUNT_BY_ID_QUERY = """
            SELECT bankaccounts.id, bankaccounts.users_id, bankaccounts.tariffs_id, bankaccounts.balance
            FROM internetprovider.bankaccounts
            WHERE bankaccounts.id=?""";
    private static final String FIND_BANKACCOUNT_BY_USER_ID_QUERY = """
            SELECT bankaccounts.id, bankaccounts.users_id, bankaccounts.tariffs_id, bankaccounts.balance
            FROM internetprovider.bankaccounts
            WHERE bankaccounts.users_id=?""";
    private static final String INSERT_BANKACCOUNT_QUERY = """
            INSERT INTO internetprovider.bankaccounts (users_id, balance)
            VALUES (?, ?)""";
    private static final String UPDATE_BALANCE_BY_ID_QUERY = """
            UPDATE internetprovider.bankaccounts SET balance = ? WHERE (id = ?)""";
    private static final String UPDATE_TARIFF_ID_BY_ID_QUERY = """
            UPDATE internetprovider.bankaccounts SET tariffs_id = ? WHERE (id = ?)""";
    private static final String UPDATE_TARIFF_ID_BY_USERS_ID_QUERY = """
            UPDATE internetprovider.bankaccounts SET tariffs_id = ? WHERE (users_id = ?)""";
    private static final String DELETE_TARIFF_ID_WHERE_TARIFF_IS_DEACTIVATED = """
            UPDATE 
                 internetprovider.bankaccounts
            INNER JOIN
                 internetprovider.tariffs
            ON
             	bankaccounts.tariffs_id = tariffs.id
            SET
             	bankaccounts.tariffs_id=NULL
            WHERE tariffs.status='DEACTIVATED';""";

    public BankAccountDaoImpl() {
        super(RowMapperHolder.getInstance().getBankAccountRowMapper());
    }

    @Override
    public Optional<BankAccount> findByUserId(long userId) throws DaoException {
        List<BankAccount> tariffList = executeQuery(FIND_BANKACCOUNT_BY_USER_ID_QUERY, userId);
        return tariffList.size() != 1 ? Optional.empty() : Optional.of(tariffList.get(0));
    }

    @Override
    public List<BankAccount> findAll() throws DaoException {
        return executeQuery(FIND_ALL_BANKACCOUNT_QUERY);
    }

    @Override
    public Optional<BankAccount> findById(long id) throws DaoException {
        List<BankAccount> tariffList = executeQuery(FIND_BANKACCOUNT_BY_ID_QUERY, id);
        return tariffList.size() != 1 ? Optional.empty() : Optional.of(tariffList.get(0));
    }

    @Override
    public long create(BankAccount bankAccount) throws DaoException {
        return executeInsertQuery(INSERT_BANKACCOUNT_QUERY,
                bankAccount.getUserId(), bankAccount.getBalance());
    }

    @Override
    public void updateBalance(long bankAccountId, BigDecimal newBalance) throws DaoException {
        executeUpdateQuery(UPDATE_BALANCE_BY_ID_QUERY, newBalance, bankAccountId);
    }

    @Override
    public void updateTariffIdByBankAccountId(long bankAccountId, Long tariffId) throws DaoException {
        executeUpdateQuery(UPDATE_TARIFF_ID_BY_ID_QUERY, tariffId, bankAccountId);
    }

    @Override
    public void updateTariffIdByUserId(long userId, Long tariffId) throws DaoException {
        executeUpdateQuery(UPDATE_TARIFF_ID_BY_USERS_ID_QUERY, tariffId, userId);
    }

    @Override
    public void deleteTariffIdWhereTariffIsDeactivated() throws DaoException {
        executeUpdateQuery(DELETE_TARIFF_ID_WHERE_TARIFF_IS_DEACTIVATED);
    }
}
