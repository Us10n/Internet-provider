package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.User;
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
    private static final String DELETE_ERROR_ATTRIBUTE = "deleteError";
    private static final String UNVERIFIED_ERROR_ATTRIBUTE = "unverifiedError";
    private static final String USER_ATTRIBUTE = "user";
    private static final String LOG_IN_PAGE = "?command=login";
    private static final String PROFILE_PAGE = "?command=profile";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        UserService userService = ServiceHolder.getInstance().getUserService();

        try {
            Optional<User> optionalUser = userService.findUserByEmailAndPassword(email, password);
           if (optionalUser.isEmpty()) {
                request.getSession().setAttribute(LOG_IN_ERROR_ATTRIBUTE, true);
                return new CommandResult(LOG_IN_PAGE, CommandType.REDIRECT);
            }
            String page;
            switch (optionalUser.get().getStatus()) {
                case VERIFIED, BLOCKED -> {
                    page = PROFILE_PAGE;
                    request.getSession().setAttribute(USER_ATTRIBUTE, optionalUser.get());
                }
                case UNVERIFIED -> {
                    page = LOG_IN_PAGE;
                    request.getSession().setAttribute(UNVERIFIED_ERROR_ATTRIBUTE, true);
                }
                case DELETED -> {
                    page = LOG_IN_PAGE;
                    request.getSession().setAttribute(DELETE_ERROR_ATTRIBUTE, true);
                }
                default -> {
                    page = LOG_IN_PAGE;
                    request.getSession().setAttribute(LOG_IN_ERROR_ATTRIBUTE, true);
                }
            }
            return new CommandResult(page, CommandType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Log in user error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
    }
}
