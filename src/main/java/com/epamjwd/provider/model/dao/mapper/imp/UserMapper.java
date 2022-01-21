package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.model.dao.mapper.ColumnName;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.entity.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (hasColumn(resultSet, ColumnName.FEEDBACK_USER_ID)) {
            user.setId(resultSet.getLong(ColumnName.FEEDBACK_USER_ID));
        } else if (hasColumn(resultSet, ColumnName.BANK_ACCOUNT_USER_ID)) {
            user.setId(resultSet.getLong(ColumnName.BANK_ACCOUNT_USER_ID));
        } else {
            user.setId(resultSet.getLong(ColumnName.ID));
        }
        user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
        user.setName(resultSet.getString(ColumnName.USER_FIRST_NAME));
        user.setSurname(resultSet.getString(ColumnName.USER_LAST_NAME));
        user.setRole(Role.valueOf(resultSet.getString(ColumnName.USER_ROLE)));
        user.setStatus(UserStatus.valueOf(resultSet.getString(ColumnName.USER_STATUS)));
        return user;
    }
}
