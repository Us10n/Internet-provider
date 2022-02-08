package com.epamjwd.provider.model.dao.mapper;

import com.epamjwd.provider.model.entity.Identifiable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * The interface Row mapper. Implements by all mapper classes.
 *
 * @param <T> indicates belonging to Identifiable interface.
 */
public interface RowMapper<T extends Identifiable> {

    /**
     * Maps the result set to class that extends Identifiable.
     *
     * @param resultSet the result set
     * @return the instance of class T
     * @throws SQLException the sql exception
     */
    T map(ResultSet resultSet) throws SQLException;

    /**
     * Check if column exists in the result set.
     *
     * @param resultSet  the result set
     * @param columnName the column name
     * @return true if exists, otherwise returns false.
     */
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
