package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    boolean registerUser(String firstName, String lastName, String email,String password) throws ServiceException;
    boolean verifyUser(String token) throws ServiceException;
}

