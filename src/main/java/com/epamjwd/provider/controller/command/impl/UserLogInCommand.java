package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.pool.ActiveUserPool;
import com.epamjwd.provider.model.service.BankAccountService;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserLogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LOG_IN_ERROR_ATTRIBUTE = "loginError";
    private static final String BAN_ERROR_ATTRIBUTE = "bannedError";
    private static final String UNVERIFIED_ERROR_ATTRIBUTE = "unverifiedError";
    private static final String USER_ATTRIBUTE = "user";
    private static final String BANK_ACCOUNT_ATTRIBUTE = "bankAccount";
    private static final String PROFILE_PAGE = "?command=profile";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        UserService userService = ServiceHolder.getInstance().getUserService();
        try {
            Optional<User> optionalUser = userService.findUserByEmailAndPassword(email, password);
            if (optionalUser.isEmpty()) {
                request.setAttribute(LOG_IN_ERROR_ATTRIBUTE, true);
                return new CommandResult(PagePath.LOG_IN_PAGE, CommandType.FORWARD);
            }
            User loadedUser = optionalUser.get();
            BankAccountService bankAccountService = ServiceHolder.getInstance().getBankAccountService();
            BankAccount bankAccount = bankAccountService.findBankAccountByUserId(loadedUser.getId());
            String page;
            CommandType commandType;
            switch (loadedUser.getStatus()) {
                case VERIFIED, BLOCKED -> {
                    page = PROFILE_PAGE;
                    commandType = CommandType.REDIRECT;
                    request.getSession().setAttribute(USER_ATTRIBUTE, loadedUser);
                    request.getSession().setAttribute(BANK_ACCOUNT_ATTRIBUTE, bankAccount);
                    ActiveUserPool.getInstance().addUser(loadedUser.getEmail());
                }
                case UNVERIFIED -> {
                    page = PagePath.LOG_IN_PAGE;
                    commandType = CommandType.FORWARD;
                    request.setAttribute(UNVERIFIED_ERROR_ATTRIBUTE, true);
                }
                case BANNED -> {
                    page = PagePath.LOG_IN_PAGE;
                    commandType = CommandType.FORWARD;
                    request.setAttribute(BAN_ERROR_ATTRIBUTE, true);
                }
                default -> {
                    page = PagePath.LOG_IN_PAGE;
                    commandType = CommandType.FORWARD;
                    request.setAttribute(LOG_IN_ERROR_ATTRIBUTE, true);
                }
            }
            return new CommandResult(page, commandType);
        } catch (ServiceException e) {
            logger.error("Log in user error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
    }
}