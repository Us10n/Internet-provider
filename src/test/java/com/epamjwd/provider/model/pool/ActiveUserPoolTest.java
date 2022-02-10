package com.epamjwd.provider.model.pool;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ActiveUserPoolTest {

    @Test
    public void testAddUser() {
        String userEmail="userEmail";
        ActiveUserPool.getInstance().addUser(userEmail);
        boolean actual=ActiveUserPool.getInstance().isActive(userEmail);
        Assert.assertTrue(actual);
    }

    @Test
    public void testRemoveUser() {
        String someEmail="someEmail";
        ActiveUserPool.getInstance().addUser(someEmail);
        boolean actual=ActiveUserPool.getInstance().removeUser(someEmail);
        Assert.assertTrue(actual);
    }
}