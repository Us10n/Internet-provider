package com.epamjwd.provider.model.service.validator;

public interface UserValidator {
    boolean isNameValid(String name);
    boolean isEmailValid(String email);
    boolean isPasswordValid(String password);
}
