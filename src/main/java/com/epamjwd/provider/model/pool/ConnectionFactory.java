package com.epamjwd.provider.model.pool;

import com.epamjwd.provider.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USER = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final String DATABASE_DRIVER = "db.driver";

    public static ProxyConnection createConnection(ResourceBundle dbBundle) throws ConnectionException {
        ProxyConnection proxyConnection = null;
        try {
            String dbUrl = dbBundle.getString(DATABASE_URL);
            String dbUser = dbBundle.getString(DATABASE_USER);
            String dbPassword = dbBundle.getString(DATABASE_PASSWORD);
            Class.forName(dbBundle.getString(DATABASE_DRIVER));
            proxyConnection = new ProxyConnection(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        } catch (SQLException e) {
            logger.error("DB connection error", e);
            throw new ConnectionException(e);
        } catch (ClassNotFoundException e) {
            logger.error("MYSQL driver not found", e);
            throw new ConnectionException(e);
        }
        return proxyConnection;
    }
}
