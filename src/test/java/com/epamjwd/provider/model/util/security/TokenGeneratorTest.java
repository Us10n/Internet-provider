package com.epamjwd.provider.model.util.security;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TokenGeneratorTest {

    @Test
    public void testGenerateToken() {
        TokenGenerator tokenGenerator = TokenGenerator.getInstance();
        String firstToken = tokenGenerator.generateToken();
        String secondToken = tokenGenerator.generateToken();
        Assert.assertNotEquals(firstToken, secondToken);
    }
}