package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    boolean registerUser(String firstName, String lastName, String email,String password) throws ServiceException;
    boolean verifyUser(String token) throws ServiceException;
    boolean updateFirstName(long userId, String newFirstName) throws ServiceException;
    boolean updateLastName(long userId, String newLastName) throws ServiceException;
    boolean updatePassword(long userId, String password) throws ServiceException;
}

