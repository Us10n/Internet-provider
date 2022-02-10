package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.exception.PoolException;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.entity.UserStatus;
import com.epamjwd.provider.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperTest {

    @Test
    public void testMap() throws PoolException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();

        String query = "SELECT users.id, users.email, users.first_name, users.last_name, users.role, users.status FROM users WHERE users.id='1'";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            UserMapper userMapper = new UserMapper();
            User actual = userMapper.map(resultSet);
            User expected = new User();
            expected.setId(1);
            expected.setEmail("test@test.com");
            expected.setName("S");
            expected.setSurname("R");
            expected.setRole(Role.ADMIN);
            expected.setStatus(UserStatus.VERIFIED);

            Assert.assertEquals(actual, expected);
        }
    }
}