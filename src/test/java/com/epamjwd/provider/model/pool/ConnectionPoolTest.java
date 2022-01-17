package com.epamjwd.provider.model.pool;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConnectionPoolTest {

    @Test
    void testConnectionPoolOnSingleton() {
        ConnectionPool poolFirst = ConnectionPool.getInstance();
        ConnectionPool poolSecond = ConnectionPool.getInstance();

        assertEquals(poolFirst, poolSecond);
    }


}