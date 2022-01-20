package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {

    private static final String VALID_NAME_REGEX = "^[A-Za-zА-Яа-я]{1,20}$";
    private static final String VALID_EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String VALID_PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @Override
    public boolean isNameValid(String name) {
        return name.matches(VALID_NAME_REGEX);
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.matches(VALID_EMAIL_REGEX) && email.length() <= 25;
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password.matches(VALID_PASSWORD_REGEX) && password.length() <= 20;
    }
}
