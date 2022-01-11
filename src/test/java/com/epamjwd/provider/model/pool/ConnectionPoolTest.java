package com.epamjwd.provider.model.pool;

import com.epamjwd.provider.exception.ConnectionException;
import org.testng.annotations.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    @Test
    void testConnectionPoolOnSingleton() {
        ConnectionPool poolFirst = ConnectionPool.getInstance();
        ConnectionPool poolSecond = ConnectionPool.getInstance();

        assertEquals(poolFirst, poolSecond);
    }

    @Test(expectedExceptions = ConnectionException.class)
    void testOnConnectionAmount() throws ConnectionException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
//todo doesnt throw exception
        BlockingDeque<Integer> deque = new LinkedBlockingDeque<Integer>(1);
        deque.add(1);
        try {
            deque.take();
            deque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 12; i++) {
            connectionPool.getConnection();
        }
    }

}