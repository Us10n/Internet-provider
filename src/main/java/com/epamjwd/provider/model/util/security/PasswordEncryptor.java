package com.epamjwd.provider.model.util.security;

import com.epamjwd.provider.exception.UtilityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
    private static final Logger logger = LogManager.getLogger();
    private static PasswordEncryptor instance;

    private PasswordEncryptor() {
    }

    public static PasswordEncryptor getInstance() {
        if (instance == null) {
            instance = new PasswordEncryptor();
        }
        return instance;
    }

    public String encryptPassword(String password) throws UtilityException {
        StringBuilder encryptedPasswordBuilder = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] encryptedBytes = md5.digest(password.getBytes(StandardCharsets.UTF_8));
            for (byte b : encryptedBytes) {
                encryptedPasswordBuilder.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Password encryption error");
            throw new UtilityException("Password encryption error", e);
        }
        return encryptedPasswordBuilder.toString();
    }
}
