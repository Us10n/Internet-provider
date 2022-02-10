package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserValidatorImplTest {
    UserValidator userValidator = UserValidatorImpl.getInstance();

    @Test
    public void testIsFirstNameValid() {
        String firstName = "Name";
        boolean actual = userValidator.isFirstNameValid(firstName);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsLastNameValid() {
        String lastName = "Surname";
        boolean actual = userValidator.isLastNameValid(lastName);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsEmailValid() {
        String email = "example@example.com";
        boolean actual = userValidator.isEmailValid(email);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsPasswordValid() {
        String password = "12345678";
        boolean actual = userValidator.isPasswordValid(password);
        Assert.assertTrue(actual);
    }
}