package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandName;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowUserPanelPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String FIRST_NAME_SORT_PARAMETER = "firstName";
    private static final String EMAIL_SORT_PARAMETER = "email";
    private static final String STATUS_SORT_PARAMETER = "status";
    private static final String ROLE_SORT_PARAMETER = "role";
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String USER_LIST_ATTRIBUTE = "users";
    private static final String CURRENT_PAGE = "?command=usersPanel&sort=";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        List<User> userList = new ArrayList<>();
        UserService userService = ServiceHolder.getInstance().getUserService();

        String sortParameter = request.getParameter(CommandName.SORT) == null ?
                FIRST_NAME_SORT_PARAMETER : request.getParameter(CommandName.SORT);

        try {
            userList = switch (sortParameter) {
                case EMAIL_SORT_PARAMETER -> userService.findUsersSortByEmail();
                case STATUS_SORT_PARAMETER -> userService.findUsersSortByStatus();
                case ROLE_SORT_PARAMETER -> userService.findUsersSortByRole();
                default -> userService.findUsersSortByFirstName();
            };
        } catch (ServiceException e) {
            logger.error("Find and sort users error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
        request.setAttribute(USER_LIST_ATTRIBUTE, userList);
        request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE + sortParameter);
        return new CommandResult(PagePath.USERS_PANEL_PAGE, CommandType.FORWARD);
    }
}
