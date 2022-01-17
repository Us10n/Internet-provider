package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.PoolException;
import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.entity.Identifiable;
import com.epamjwd.provider.model.pool.ConnectionPool;
import com.epamjwd.provider.model.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQueryExecutor<T extends Identifiable> {

    private static final Logger logger = LogManager.getLogger();
    private RowMapper<T> rowMapper;

    public AbstractQueryExecutor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, Object... parameters) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, parameters);
             ResultSet resultSet = statement.executeQuery()) {
            entities = createEntitiesList(resultSet);
        } catch (SQLException e) {
            logger.error("Find query execution error", e);
            throw new DaoException("Find query execution error", e);
        }
        return entities;
    }

    protected long executeInsertQuery(String query, Object... params) throws DaoException {
        long result = 0;
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                result = generatedKey.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("Insert query execution error", e);
            throw new DaoException("Insert query execution error", e);
        }
        return result;
    }

    protected void executeUpdateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update query execution error", e);
            throw new DaoException("Update query execution error", e);
        }
    }

    private List<T> createEntitiesList(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T entity = rowMapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            logger.error("Entity create error", e);
            throw new DaoException("Entity create error", e);
        }
        return entities;
    }

    private PreparedStatement createStatement(String query, Object... params) throws DaoException {
        try {
            ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = proxyConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            return preparedStatement;
        } catch (SQLException e) {
            logger.error("Create statement error", e);
            throw new DaoException("Create statement error", e);
        } catch (PoolException e) {
            logger.error("Get connection error", e);
            throw new DaoException("Get connection error", e);
        }
    }


}
