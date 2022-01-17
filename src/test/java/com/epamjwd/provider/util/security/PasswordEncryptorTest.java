package com.epamjwd.provider.util.security;

import com.epamjwd.provider.exception.UtilityException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordEncryptorTest {

    @Test
    public void testEncryptPassword() throws UtilityException {
        String originalString = "Hello world";
        String expected = "3e25960a79dbc69b674cd4ec67a72c62";
        String actual = PasswordEncryptor.encryptPassword(originalString);
        Assert.assertEquals(actual,expected);
    }
}