package com.epamjwd.provider.model.dao.mapper;

import com.epamjwd.provider.model.entity.Identifiable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {

    T map(ResultSet resultSet) throws SQLException;

    default boolean hasColumn(ResultSet resultSet, String columnName) {
        Logger logger = LogManager.getLogger();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                if (columnName.equals(metaData.getColumnName(i))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Result set doesn't contain column=" + columnName);
        }
        return false;
    }
}
