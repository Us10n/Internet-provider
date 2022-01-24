package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.entity.UserStatus;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    Optional<User> findByToken(String token) throws DaoException;
    void updateFirstName(long userId, String firstName) throws DaoException;
    void updateLastName(long userId, String lastName) throws DaoException;
    void updateStatus(long userId, UserStatus userStatus) throws DaoException;
    void updateRole(long userId, Role role) throws DaoException;
    void updatePassword(long userId, String newPassword) throws DaoException;
}
