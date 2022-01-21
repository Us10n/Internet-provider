package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;


public class ShowLogInPageCommand implements Command {
    private static final String LOG_IN_ERROR_ATTRIBUTE = "loginError";
    private static final String DELETE_ERROR_ATTRIBUTE = "deleteError";
    private static final String UNVERIFIED_ERROR_ATTRIBUTE = "unverifiedError";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        Object logInErrorAttribute = request.getSession().getAttribute(LOG_IN_ERROR_ATTRIBUTE);
        Object deleteErrorAttribute = request.getSession().getAttribute(DELETE_ERROR_ATTRIBUTE);
        Object unverifiedErrorAttribute = request.getSession().getAttribute(UNVERIFIED_ERROR_ATTRIBUTE);

        if (logInErrorAttribute != null) {
            request.getSession().removeAttribute(LOG_IN_ERROR_ATTRIBUTE);
            request.setAttribute(LOG_IN_ERROR_ATTRIBUTE, logInErrorAttribute);
        }
        if (deleteErrorAttribute != null) {
            request.getSession().removeAttribute(DELETE_ERROR_ATTRIBUTE);
            request.setAttribute(DELETE_ERROR_ATTRIBUTE, deleteErrorAttribute);
        }
        if (unverifiedErrorAttribute != null) {
            request.getSession().removeAttribute(UNVERIFIED_ERROR_ATTRIBUTE);
            request.setAttribute(UNVERIFIED_ERROR_ATTRIBUTE, unverifiedErrorAttribute);
        }


        request.getSession().setAttribute("currentPage", "?command=login");
        return new CommandResult(PagePath.LOG_IN_PAGE, CommandType.FORWARD);
    }
}
