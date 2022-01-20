package com.epamjwd.provider.model.service;

public interface UserService {
    void findUserByEmailAndPassword(String email, String password);
    void registerUser(String firstName, String lastName, String email,String password);
}
