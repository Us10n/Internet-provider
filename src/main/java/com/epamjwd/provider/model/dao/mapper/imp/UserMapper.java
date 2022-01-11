package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.model.dao.mapper.ColumnName;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ColumnName.ID));
        user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
        user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
        user.setName(resultSet.getString(ColumnName.USER_FIRST_NAME));
        user.setSurname(resultSet.getString(ColumnName.USER_LAST_NAME));
        RowMapper<Role> roleMapper = RowMapperFactory.getInstance().getRoleRowMapper();
        Role userRole = roleMapper.map(resultSet);


        return null;
    }
}
