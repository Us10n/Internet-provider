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

public class TariffMapperTest {

    @Test
    void mapFull() throws PoolException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = """
            SELECT tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id
            WHERE tariffs.id='1'""";

        try(PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            List<Identifiable> tariffs = new ArrayList<>();
            while (resultSet.next()) {
                tariffs.add(RowMapperFactory.getInstance().getTariffRowMapper().map(resultSet));
            }

            String actual = ((Tariff) tariffs.get(0)).getName();
            String expected = "Start";
            Assert.assertEquals(actual, expected);
        }
    }

}