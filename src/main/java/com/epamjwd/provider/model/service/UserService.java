package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Find users sort by first name list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersSortByFirstName() throws ServiceException;

    /**
     * Find users sort by email list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersSortByEmail() throws ServiceException;

    /**
     * Find users sort by role list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersSortByRole() throws ServiceException;

    /**
     * Find users sort by status list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findUsersSortByStatus() throws ServiceException;

    /**
     * Find user by email and password optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Find user by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserById(Long userId) throws ServiceException;

    /**
     * Register user boolean.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param password  the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean registerUser(String firstName, String lastName, String email,String password) throws ServiceException;

    /**
     * Verify user boolean.
     *
     * @param token the token
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean verifyUser(String token) throws ServiceException;

    /**
     * Update first name boolean.
     *
     * @param userId       the user id
     * @param newFirstName the new first name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateFirstName(long userId, String newFirstName) throws ServiceException;

    /**
     * Update last name boolean.
     *
     * @param userId      the user id
     * @param newLastName the new last name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateLastName(long userId, String newLastName) throws ServiceException;

    /**
     * Update password boolean.
     *
     * @param userId   the user id
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePassword(long userId, String password) throws ServiceException;

    /**
     * Make user admin boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean makeUserAdmin(String userId) throws ServiceException;

    /**
     * Make user verified boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean makeUserVerified(String userId) throws ServiceException;

    /**
     * Make user banned boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean makeUserBanned(String userId) throws ServiceException;

    /**
     * Make user unbanned boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean makeUserUnbanned(String userId) throws ServiceException;
}

