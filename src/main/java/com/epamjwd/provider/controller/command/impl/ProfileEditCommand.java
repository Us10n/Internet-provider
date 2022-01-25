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
import javax.servlet.http.HttpSession;

public class ProfileEditCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NEW_FIRST_NAME_PARAMETER = "newFirstName";
    private static final String NEW_LAST_NAME_PARAMETER = "newLastName";
    private static final String NEW_PASSWORD_PARAMETER = "newPassword";
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    private static final String FIRST_NAME_UPDATE_ERROR_ATTRIBUTE = "firstNameError";
    private static final String LAST_NAME_UPDATE_ERROR_ATTRIBUTE = "lastNameError";
    private static final String PASSWORD_UPDATE_ERROR_ATTRIBUTE = "passwordError";
    private static final String PROFILE_PAGE = "?command=profile";
    private static final String LOG_OUT_PAGE = "?command=logoutUser";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String newFirstName = request.getParameter(NEW_FIRST_NAME_PARAMETER);
        String newLastName = request.getParameter(NEW_LAST_NAME_PARAMETER);
        String newPassword = request.getParameter(NEW_PASSWORD_PARAMETER);
        Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
        String userEmail = (String) session.getAttribute(USER_EMAIL_ATTRIBUTE);

        UserService userService = ServiceHolder.getInstance().getUserService();

        try {
            User user = userService.findUserByEmail(userEmail).get();
//todo
            boolean isFirstNameSuccessfullyChanged = false;
            String page = PROFILE_PAGE;
            CommandType commandType = CommandType.REDIRECT;
            if (newFirstName != null && !user.getName().equals(newFirstName)) {
                boolean updateFirstNameStatus = userService.updateFirstName(userId, newFirstName);
                if (!updateFirstNameStatus) {
                    page = PagePath.PROFILE_PAGE;
                    commandType = CommandType.FORWARD;
                }
                request.setAttribute(FIRST_NAME_UPDATE_ERROR_ATTRIBUTE, !updateFirstNameStatus);
            }
            if (newLastName != null && !user.getSurname().equals(newLastName)) {
                boolean updateLastNameStatus = userService.updateLastName(userId, newLastName);
                if (!updateLastNameStatus) {
                    page = PagePath.PROFILE_PAGE;
                    commandType = CommandType.FORWARD;
                }
                request.setAttribute(LAST_NAME_UPDATE_ERROR_ATTRIBUTE, !updateLastNameStatus);
            }
            if (newPassword != null && !newPassword.isBlank()) {
                boolean updatePasswordStatus = userService.updatePassword(userId, newPassword);
                if (updatePasswordStatus) {
                    page = LOG_OUT_PAGE;
                    commandType = CommandType.REDIRECT;
                }
                request.setAttribute(PASSWORD_UPDATE_ERROR_ATTRIBUTE, !updatePasswordStatus);
            }
            return new CommandResult(page, commandType);
        } catch (ServiceException e) {
            logger.error("Personal information update error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }

    }
}
