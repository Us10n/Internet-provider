package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.exception.UtilityException;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.UserService;
import com.epamjwd.provider.model.util.security.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ProfileEditCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NEW_FIRST_NAME_PARAMETER = "newFirstName";
    private static final String NEW_LAST_NAME_PARAMETER = "newLastName";
    private static final String NEW_PASSWORD_PARAMETER = "newPassword";
    private static final String USER_ATTRIBUTE = "user";
    private static final String FIRST_NAME_UPDATE_ERROR_ATTRIBUTE = "firstNameError";
    private static final String LAST_NAME_UPDATE_ERROR_ATTRIBUTE = "lastNameError";
    private static final String PASSWORD_UPDATE_ERROR_ATTRIBUTE = "passwordError";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String newFirstName = request.getParameter(NEW_FIRST_NAME_PARAMETER);
        String newLastName = request.getParameter(NEW_LAST_NAME_PARAMETER);
        String newPassword = request.getParameter(NEW_PASSWORD_PARAMETER);
        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);

        UserService userService = ServiceHolder.getInstance().getUserService();

        try {
            if (newFirstName != null && !newFirstName.equals(user.getName())) {
                boolean updateFirstNameStatus = userService.updateFirstName(user.getId(), newFirstName);
                if (updateFirstNameStatus) {
                    user.setName(newFirstName);
                }
                request.setAttribute(FIRST_NAME_UPDATE_ERROR_ATTRIBUTE, !updateFirstNameStatus);
            }
            if (newLastName != null && !newLastName.equals(user.getSurname())) {
                boolean updateLastNameStatus = userService.updateLastName(user.getId(), newLastName);
                if (updateLastNameStatus) {
                    user.setSurname(newLastName);
                }
                request.setAttribute(LAST_NAME_UPDATE_ERROR_ATTRIBUTE, !updateLastNameStatus);
            }
            if (newPassword != null && !newPassword.isBlank()) {
                String encryptedNewPassword = PasswordEncryptor.getInstance().encryptPassword(newPassword);
                if (!newPassword.equals(encryptedNewPassword)) {
                    boolean updatePasswordStatus = userService.updatePassword(user.getId(), newPassword);
                    request.setAttribute(PASSWORD_UPDATE_ERROR_ATTRIBUTE, !updatePasswordStatus);
                }
            }
            request.getSession().setAttribute(USER_ATTRIBUTE, user);
            return new CommandResult(PagePath.PROFILE_PAGE, CommandType.FORWARD);
        } catch (ServiceException | UtilityException e) {
            logger.error("Personal information update error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }

    }
}
