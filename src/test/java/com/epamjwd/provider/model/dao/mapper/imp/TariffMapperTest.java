package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.exception.PoolException;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Identifiable;
import com.epamjwd.provider.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TariffMapperTest {

    @Test
    void mapFull() throws PoolException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "DELETE FROM `internetprovider`.`tariffs` WHERE (`id` = '6');";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Identifiable> tariffs = new ArrayList<>();
        while (resultSet.next()) {
            tariffs.add(RowMapperFactory.getInstance().getTariffRowMapper().map(resultSet));
        }
        System.out.println(tariffs);

        int actual = tariffs.size();
        int expected = 5;
        Assert.assertEquals(actual, expected);
    }

}