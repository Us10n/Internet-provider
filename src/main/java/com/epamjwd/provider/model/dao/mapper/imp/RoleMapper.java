package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.model.dao.mapper.ColumnName;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role map(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        if (hasColumn(resultSet, ColumnName.USER_ROLE_ID)) {
            role.setId(resultSet.getLong(ColumnName.USER_ROLE_ID));
        } else {
            role.setId(resultSet.getLong(ColumnName.ID));
        }
        role.setRole(resultSet.getString(ColumnName.ROLE_NAME));
        return role;
    }
}
