package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserSignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIRST_NAME_PARAMETER = "firstName";
    private static final String LAST_NAME_PARAMETER = "lastName";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String SIGN_UP_ERROR_ATTRIBUTE = "signupError";
    private static final String USER_EXISTS_ERROR_ATTRIBUTE = "existsError";
    private static final String LOG_IN_PAGE = "?command=login";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String firstName = request.getParameter(FIRST_NAME_PARAMETER);
        String lastName = request.getParameter(LAST_NAME_PARAMETER);
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        UserService userService = ServiceHolder.getInstance().getUserService();
        try {
            boolean registrationStatus = userService.registerUser(firstName, lastName, email, password);
            if (!registrationStatus) {
                Optional<User> user = userService.findUserByEmail(email);
                if (user.isEmpty()) {
                    request.setAttribute(SIGN_UP_ERROR_ATTRIBUTE, true);
                } else {
                    request.setAttribute(USER_EXISTS_ERROR_ATTRIBUTE, true);
                }
                return new CommandResult(PagePath.SIGN_UP_PAGE, CommandType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Registration user error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
        return new CommandResult(LOG_IN_PAGE, CommandType.REDIRECT);
    }
}
