package com.epamjwd.provider.model.dao.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.AbstractQueryExecutor;
import com.epamjwd.provider.model.dao.UserDao;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractQueryExecutor<User> implements UserDao {

    private static final String FIND_ALL_USERS_QUERY = """
            SELECT users.id, users.password, users.email, users.first_name, users.last_name, users.role, users.token FROM users""";
    private static final String FIND_USER_BY_ID_QUERY = """
            SELECT users.id, users.password, users.email, users.first_name, users.last_name, users.role, users.token FROM users
            WHERE id=?""";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY = """
            SELECT users.id, users.password, users.email, users.first_name, users.last_name, users.role, users.token FROM users
            WHERE email=? AND password=?""";
    private static final String INSERT_USER_QUERY = """
            INSERT INTO internetprovider.users (role, email, password, first_name, last_name,token) 
            VALUES (?, ?, ?, ?, ?, ?)""";
    private static final String UPDATE_NAME_QUERY = """
            UPDATE internetprovider.users SET first_name=?
            WHERE (id=?)""";
    private static final String UPDATE_SURNAME_QUERY = """
            UPDATE internetprovider.users SET last_name=?
            WHERE (id=?)""";
    private static final String UPDATE_STATUS_QUERY = """
            UPDATE internetprovider.users SET status=?
            WHERE (id=?)""";
    private static final String UPDATE_ROLE_QUERY = """
            UPDATE internetprovider.users SET role=?
            WHERE (id=?)""";
    private static final String UPDATE_PASSWORD_QUERY = """
            UPDATE internetprovider.users SET password=?
            WHERE (id=?)""";

    public UserDaoImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper());
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(FIND_ALL_USERS_QUERY);
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        List<User> userList = executeQuery(FIND_USER_BY_ID_QUERY, id);
        return userList.size() != 1 ? Optional.empty() : Optional.of(userList.get(0));
    }

    @Override
    public long create(User user) throws DaoException {
        return executeInsertQuery(INSERT_USER_QUERY,
                user.getRole(), user.getEmail(), user.getPassword(), user.getName(), user.getSurname());
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        List<User> userList = executeQuery(FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY, email, password);
        return userList.size() != 1 ? Optional.empty() : Optional.of(userList.get(0));
    }

    @Override
    public void updateName(long userId, String newName) throws DaoException {
        executeUpdateQuery(UPDATE_NAME_QUERY, newName, userId);
    }

    @Override
    public void updateSurname(long userId, String newSurname) throws DaoException {
        executeUpdateQuery(UPDATE_SURNAME_QUERY, newSurname, userId);
    }

    @Override
    public void updateStatus(long userId, UserStatus userStatus) throws DaoException {
        executeUpdateQuery(UPDATE_STATUS_QUERY, userStatus.toString(), userId);
    }

    @Override
    public void updateRole(long userId, Role role) throws DaoException {
        executeUpdateQuery(UPDATE_ROLE_QUERY, role.toString(), userId);
    }

    @Override
    public void updatePassword(long userId, String newPassword) throws DaoException {
        executeUpdateQuery(UPDATE_PASSWORD_QUERY, newPassword, userId);
    }
}