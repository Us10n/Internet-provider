package com.epamjwd.provider.model.pool;


import com.epamjwd.provider.exception.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();
    private static final Lock createPoolLock = new ReentrantLock();
    private static final String DEFAULT_POOL_SIZE = "db.pool.size";
    private static final String DATABASE_PROPERTY_PATH = "connection.database";

    private static ConnectionPool instance;
    private static AtomicBoolean isPoolCreated = new AtomicBoolean(false);

    private BlockingDeque<ProxyConnection> availableConnections;
    private BlockingDeque<ProxyConnection> usedConnections;

    private ConnectionPool() {
        try {
            ResourceBundle dbBundle = ResourceBundle.getBundle(DATABASE_PROPERTY_PATH);
            int poolSize = Integer.parseInt(dbBundle.getString(DEFAULT_POOL_SIZE));
            availableConnections = new LinkedBlockingDeque<>(poolSize);
            usedConnections = new LinkedBlockingDeque<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection proxyConnection = ConnectionFactory.createConnection(dbBundle);
                availableConnections.add(proxyConnection);
            }
        } catch (Exception e) {
            logger.error("DB properties load error", e);
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!isPoolCreated.get()) {
            try {
                createPoolLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isPoolCreated.set(true);
                }
            } finally {
                createPoolLock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() throws PoolException {
        ProxyConnection proxyConnection;
        try {
            proxyConnection = availableConnections.take();
            usedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("Get connection error", e);
            Thread.currentThread().interrupt();
            throw new PoolException("Get connection error", e);
        }
        return proxyConnection;
    }

    public boolean releaseConnection(ProxyConnection proxyConnection) throws PoolException {
        if (proxyConnection != null) {
            usedConnections.remove(proxyConnection);
            try {
                availableConnections.put(proxyConnection);
            } catch (InterruptedException e) {
                logger.error("Release connection error", e);
                throw new PoolException("Release connection error", e);
            }
            return true;
        }
        return false;
    }

    public void destroyPool() throws PoolException {
        try {
            for (ProxyConnection availableConnection : availableConnections) {
                availableConnection.reallyClose();
            }
            for (ProxyConnection usedConnection : usedConnections) {
                usedConnection.reallyClose();
            }
        } catch (SQLException e) {
            logger.error("Close connections error", e);
            throw new PoolException("Close connections error", e);
        }
    }
}
