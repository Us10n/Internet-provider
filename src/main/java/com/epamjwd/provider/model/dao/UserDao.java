package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao extends Dao<User> {

    /**
     * Find users sorted by first name.
     *
     * @return user list
     * @throws DaoException the dao exception
     */
    List<User> findUsersSortByFirstName() throws DaoException;

    /**
     * Find users sorted by email.
     *
     * @return user list
     * @throws DaoException the dao exception
     */
    List<User> findUsersSortByEmail() throws DaoException;

    /**
     * Find users sorted by role.
     *
     * @return user list
     * @throws DaoException the dao exception
     */
    List<User> findUsersSortByRole() throws DaoException;

    /**
     * Find users sort by status.
     *
     * @return user list
     * @throws DaoException the dao exception
     */
    List<User> findUsersSortByStatus() throws DaoException;

    /**
     * Find user by email and password.
     *
     * @param email    the email
     * @param password the password
     * @return founded user
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return founded user
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Find user by token.
     *
     * @param token the token
     * @return founded user
     * @throws DaoException the dao exception
     */
    Optional<User> findByToken(String token) throws DaoException;

    /**
     * Update user first name by user id.
     *
     * @param userId    the user id
     * @param firstName the first name
     * @throws DaoException the dao exception
     */
    void updateFirstName(long userId, String firstName) throws DaoException;

    /**
     * Update user last name by user id.
     *
     * @param userId   the user id
     * @param lastName the last name
     * @throws DaoException the dao exception
     */
    void updateLastName(long userId, String lastName) throws DaoException;

    /**
     * Update user status by user id.
     *
     * @param userId     the user id
     * @param userStatus the user status
     * @throws DaoException the dao exception
     */
    void updateStatus(long userId, UserStatus userStatus) throws DaoException;

    /**
     * Update user role by user id.
     *
     * @param userId the user id
     * @param role   the role
     * @throws DaoException the dao exception
     */
    void updateRole(long userId, Role role) throws DaoException;

    /**
     * Update user password by user id.
     *
     * @param userId      the user id
     * @param newPassword the new password
     * @throws DaoException the dao exception
     */
    void updatePassword(long userId, String newPassword) throws DaoException;
}
