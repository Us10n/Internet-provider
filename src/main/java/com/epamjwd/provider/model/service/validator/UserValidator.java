package com.epamjwd.provider.model.service.validator;

/**
 * The interface User validator.
 */
public interface UserValidator {
    /**
     * Validate first name.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isFirstNameValid(String name);

    /**
     * Validate last name.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isLastNameValid(String name);

    /**
     * Validate email.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isEmailValid(String email);

    /**
     * Validate password.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isPasswordValid(String password);
}
