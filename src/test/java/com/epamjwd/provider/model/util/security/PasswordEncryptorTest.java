package com.epamjwd.provider.model.util.security;

import com.epamjwd.provider.exception.UtilityException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PasswordEncryptorTest {

    @Mock
    PasswordEncryptor encryptor;

    @BeforeClass
    public void setUp(){
        encryptor= Mockito.mock(PasswordEncryptor.class);
        try {
            Mockito.when(encryptor.encryptPassword(null)).thenThrow(UtilityException.class);
        } catch (UtilityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEncryptPassword() throws UtilityException {
        String originalString = "Hello world";
        String expected = "3e25960a79dbc69b674cd4ec67a72c62";
        String actual = PasswordEncryptor.getInstance().encryptPassword(originalString);
        Assert.assertEquals(actual,expected);
    }

    @Test(expectedExceptions = {UtilityException.class})
    public void testEncryptPasswordOnException() throws UtilityException {
        encryptor.encryptPassword(null);
    }
}