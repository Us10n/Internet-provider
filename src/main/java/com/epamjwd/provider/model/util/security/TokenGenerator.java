package com.epamjwd.provider.model.util.security;

import java.security.SecureRandom;

public class TokenGenerator {

    private static SecureRandom secureRandom;
    private static TokenGenerator instance;

    private TokenGenerator() {
        secureRandom = new SecureRandom();
    }

    public static TokenGenerator getInstance() {
        if (instance == null) {
            instance = new TokenGenerator();
        }
        return instance;
    }

    public String generateToken() {
        long longToken = Math.abs(secureRandom.nextLong());
        String tokenString = Long.toString(longToken, 16);
        return tokenString;
    }
}
