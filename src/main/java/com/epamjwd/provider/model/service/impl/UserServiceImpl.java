package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.exception.UtilityException;
import com.epamjwd.provider.model.dao.BankAccountDao;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.dao.UserDao;
import com.epamjwd.provider.model.entity.BankAccount;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.entity.UserStatus;
import com.epamjwd.provider.model.service.UserService;
import com.epamjwd.provider.model.service.validator.UserValidator;
import com.epamjwd.provider.model.service.validator.impl.UserValidatorImpl;
import com.epamjwd.provider.model.util.mail.MailSender;
import com.epamjwd.provider.model.util.security.PasswordEncryptor;
import com.epamjwd.provider.model.util.security.TokenGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_TEXT = "To activate an account on the Internet Provider resource, follow the following link: ";

    @Override
    public Optional<User> findUserById(Long userId) throws ServiceException {
        if (userId == null) {
            return Optional.empty();
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            Optional<User> user;
            user = userDao.findById(userId);
            return user;
        } catch (DaoException e) {
            logger.error("Find user by id error", e);
            throw new ServiceException("Find user by id error", e);
        }
    }

    @Override
    public List<User> findUsersSortByFirstName() throws ServiceException {
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            List<User> userList;
            userList = userDao.findUsersSortByFirstName();
            return userList;
        } catch (DaoException e) {
            logger.error("Find users and sort by first name error", e);
            throw new ServiceException("Find users and sort by first name error", e);
        }
    }

    @Override
    public List<User> findUsersSortByEmail() throws ServiceException {
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            List<User> userList;
            userList = userDao.findUsersSortByEmail();
            return userList;
        } catch (DaoException e) {
            logger.error("Find users and sort by email error", e);
            throw new ServiceException("Find users and sort by email error", e);
        }
    }

    @Override
    public List<User> findUsersSortByRole() throws ServiceException {
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            List<User> userList;
            userList = userDao.findUsersSortByRole();
            return userList;
        } catch (DaoException e) {
            logger.error("Find users and sort by role error", e);
            throw new ServiceException("Find users and sort by role error", e);
        }
    }

    @Override
    public List<User> findUsersSortByStatus() throws ServiceException {
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            List<User> userList;
            userList = userDao.findUsersSortByStatus();
            return userList;
        } catch (DaoException e) {
            logger.error("Find users and sort by status error", e);
            throw new ServiceException("Find users and sort by status error", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        if (!UserValidatorImpl.getInstance().isEmailValid(email)) {
            return Optional.empty();
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            Optional<User> user;
            user = userDao.findByEmail(email);
            return user;
        } catch (DaoException e) {
            logger.error("Find user by email error", e);
            throw new ServiceException("Find user by email error", e);
        }
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        if (!isLoginFormValid(email, password)) {
            return Optional.empty();
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        Optional<User> user = Optional.empty();
        try {
            String encodedPassword = PasswordEncryptor.getInstance().encryptPassword(password);
            user = userDao.findByEmailAndPassword(email, encodedPassword);
        } catch (UtilityException e) {
            logger.error("Password encoding error", e);
        } catch (DaoException e) {
            logger.error("Find user by email and password error", e);
            throw new ServiceException("Find user by email and password error", e);
        }
        return user;
    }

    @Override
    public boolean registerUser(String firstName, String lastName, String email, String password) throws ServiceException {
        if (!isRegistrationUserFormValid(firstName, lastName, email, password)) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        BankAccountDao bankAccountDao = DaoHolder.getInstance().getBankAccountDao();
        try {
            String encodedPassword = PasswordEncryptor.getInstance().encryptPassword(password);
            String registrationToken = TokenGenerator.getInstance().generateToken();
            User user = new User(email, encodedPassword, firstName, lastName, Role.USER, registrationToken);
            if (userDao.findByEmail(email).isPresent()) {
                return false;
            }
            long userId = userDao.create(user);
            BankAccount bankAccount = new BankAccount(BigDecimal.valueOf(0), userId, null);
            bankAccountDao.create(bankAccount);
            String verificationMessage = buildVerificationMessage(registrationToken);
            MailSender.getInstance().send(email, verificationMessage);
        } catch (UtilityException e) {
            logger.error("Password encoding error", e);
            throw new ServiceException("Password encoding error", e);
        } catch (DaoException e) {
            logger.error("User create error", e);
            throw new ServiceException("User create error", e);
        }

        return true;
    }

    @Override
    public boolean verifyUser(String token) throws ServiceException {
        if (token == null) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            Optional<User> user = userDao.findByToken(token);
            if (user.isPresent() && user.get().getStatus() == UserStatus.UNVERIFIED) {
                userDao.updateStatus(user.get().getId(), UserStatus.VERIFIED);
                return true;
            }
        } catch (DaoException e) {
            logger.error("Verification error", e);
            throw new ServiceException("Verification error", e);
        }
        return false;
    }

    @Override
    public boolean updateFirstName(long userId, String newFirstName) throws ServiceException {
        if (!UserValidatorImpl.getInstance().isFirstNameValid(newFirstName)) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            userDao.updateFirstName(userId, newFirstName);
        } catch (DaoException e) {
            logger.error("First name update error", e);
            throw new ServiceException("First name update error", e);
        }
        return true;
    }

    @Override
    public boolean updateLastName(long userId, String newLastName) throws ServiceException {
        if (!UserValidatorImpl.getInstance().isLastNameValid(newLastName)) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            userDao.updateLastName(userId, newLastName);
        } catch (DaoException e) {
            logger.error("Last name update error", e);
            throw new ServiceException("Last name update error", e);
        }
        return true;
    }

    @Override
    public boolean updatePassword(long userId, String password) throws ServiceException {
        if (!UserValidatorImpl.getInstance().isPasswordValid(password)) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            String encodedPassword = PasswordEncryptor.getInstance().encryptPassword(password);
            userDao.updatePassword(userId, encodedPassword);
        } catch (UtilityException e) {
            logger.error("Password encrypt error", e);
            throw new ServiceException("Password encrypt error", e);
        } catch (DaoException e) {
            logger.error("Password update error", e);
            throw new ServiceException("Password update error", e);
        }
        return true;
    }

    @Override
    public boolean makeUserAdmin(String userId) throws ServiceException {
        if (userId == null) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            long userIdLong = Long.parseLong(userId);
            if (userDao.findById(userIdLong).isEmpty()) {
                return false;
            }
            userDao.updateRole(userIdLong, Role.ADMIN);
        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return false;
        } catch (DaoException e) {
            logger.error("Make user admin error", e);
            throw new ServiceException("Make user admin error", e);
        }
        return true;
    }

    @Override
    public boolean makeUserVerified(String userId) throws ServiceException {
        if (userId == null) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            long userIdLong = Long.parseLong(userId);
            Optional<User> optionalUser = userDao.findById(userIdLong);
            if (optionalUser.isEmpty() || optionalUser.get().getStatus() != UserStatus.UNVERIFIED) {
                return false;
            }
            userDao.updateStatus(userIdLong, UserStatus.VERIFIED);
        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return false;
        } catch (DaoException e) {
            logger.error("Make user verified error", e);
            throw new ServiceException("Make user verified error", e);
        }
        return true;
    }

    @Override
    public boolean makeUserBanned(String userId) throws ServiceException {
        if (userId == null) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            long userIdLong = Long.parseLong(userId);
            Optional<User> optionalUser = userDao.findById(userIdLong);
            if (optionalUser.isEmpty() || optionalUser.get().getRole() == Role.ADMIN) {
                return false;
            }
            userDao.updateStatus(userIdLong, UserStatus.BANNED);

        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return false;
        } catch (DaoException e) {
            logger.error("Make user banned error", e);
            throw new ServiceException("Make user banned error", e);
        }
        return true;
    }

    @Override
    public boolean makeUserUnbanned(String userId) throws ServiceException {
        if (userId == null) {
            return false;
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        try {
            long userIdLong = Long.parseLong(userId);
            Optional<User> optionalUser = userDao.findById(userIdLong);
            if (optionalUser.isEmpty() || optionalUser.get().getRole() == Role.ADMIN) {
                return false;
            }
            userDao.updateStatus(userIdLong, UserStatus.VERIFIED);
        } catch (NumberFormatException e) {
            logger.error("Number values parse error", e);
            return false;
        } catch (DaoException e) {
            logger.error("Make user unbanned error", e);
            throw new ServiceException("Make user unbanned error", e);
        }
        return true;
    }

    private String buildVerificationMessage(String token) {
        StringBuilder stringBuilder = new StringBuilder(MAIL_TEXT);
        stringBuilder.append("'http://localhost:8080/provider/controller?command=verify&token=");
        stringBuilder.append(token);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    private boolean isLoginFormValid(String email, String password) {
        UserValidator userValidator = UserValidatorImpl.getInstance();
        return userValidator.isEmailValid(email) &&
                userValidator.isPasswordValid(password);
    }

    private boolean isRegistrationUserFormValid(String firstName, String lastName, String email, String password) {
        UserValidator userValidator = UserValidatorImpl.getInstance();
        return userValidator.isFirstNameValid(firstName) &&
                userValidator.isLastNameValid(lastName) &&
                userValidator.isEmailValid(email) &&
                userValidator.isPasswordValid(password);
    }
}
