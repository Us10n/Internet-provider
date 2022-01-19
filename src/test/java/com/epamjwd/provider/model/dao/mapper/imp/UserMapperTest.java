package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.impl.UserDaoImpl;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Identifiable;
import com.epamjwd.provider.model.entity.UserStatus;
import com.epamjwd.provider.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class UserMapperTest {

    @Test
    public void testMap() throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.updateStatus(3, UserStatus.BLOCKED);

    }
}