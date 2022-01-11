package com.epamjwd.provider.model.dao.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.UserDao;
import com.epamjwd.provider.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final String FIND_ALL_USERS_QUERY = """
            SELECT users.id, users.login, users.password, users.email, users.name, users.surname, roles.role FROM users 
            JOIN roles ON users.roles_id""";
    private static final String FIND_USER_BY_ID = """
            SELECT users.id, users.login, users.password, users.email, users.name, users.surname, roles.role FROM users\s
            JOIN roles ON users.roles_id
            WHERE id=?""";

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(FIND_ALL_USERS_QUERY);
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void removeById(long id) throws DaoException {

    }

    @Override
    public long create(User entity) throws DaoException {
        return 0;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        return Optional.empty();
    }
}
