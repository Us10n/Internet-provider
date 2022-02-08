package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.exception.PoolException;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Identifiable;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BankAccountMapperTest {

    @Test
    public void testMap() throws SQLException, PoolException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = """
           SELECT bankaccounts.id, bankaccounts.users_id, bankaccounts.tariffs_id, bankaccounts.balance
                       FROM internetprovider.bankaccounts
                       WHERE bankaccounts.id='1'""";

        try(PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {
            List<Identifiable> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(RowMapperFactory.getInstance().getBankAccountRowMapper().map(resultSet));
            }
            int actual = accounts.size();
            int expected = 1;
            Assert.assertEquals(actual, expected);
        }
    }
}