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
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_TEXT = "To activate an account on the Internet Provider resource, follow the following link: ";

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        UserValidator userValidator = UserValidatorImpl.getInstance();
        if (!userValidator.isEmailValid(email)) {
            return Optional.empty();
        }
        UserDao userDao = DaoHolder.getInstance().getUserDao();
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findByEmail(email);
        } catch (DaoException e) {
            logger.error("Find user by email error", e);
            throw new ServiceException("Find user by email error", e);
        }
        return user;
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
        } catch (DaoException e) {
            logger.error("Find user by email and password error", e);
            throw new ServiceException("Find user by email and password error", e);
        } catch (UtilityException e) {
            logger.error("Password encoding error", e);
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
        } catch (DaoException e) {
            logger.error("User create error", e);
            throw new ServiceException("User create error", e);
        } catch (UtilityException e) {
            logger.error("Password encoding error", e);
            throw new ServiceException("Password encoding error", e);
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
        return userValidator.isNameValid(firstName) &&
                userValidator.isNameValid(lastName) &&
                userValidator.isEmailValid(email) &&
                userValidator.isPasswordValid(password);
    }
}
