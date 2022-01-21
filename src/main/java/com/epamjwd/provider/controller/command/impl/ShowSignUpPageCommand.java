package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ShowSignUpPageCommand implements Command {
    private static final String SIGN_UP_ERROR_ATTRIBUTE = "signupError";
    private static final String USER_EXISTS_ERROR_ATTRIBUTE = "existsError";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().setAttribute("currentPage", "?command=signup");

        Object singupErrorAttribute = request.getSession().getAttribute(SIGN_UP_ERROR_ATTRIBUTE);
        Object existsErrorAttribute = request.getSession().getAttribute(USER_EXISTS_ERROR_ATTRIBUTE);

        if (singupErrorAttribute != null) {
            request.setAttribute(SIGN_UP_ERROR_ATTRIBUTE, singupErrorAttribute);
            request.getSession().removeAttribute(SIGN_UP_ERROR_ATTRIBUTE);
        }
        if (existsErrorAttribute != null) {
            request.setAttribute(USER_EXISTS_ERROR_ATTRIBUTE, existsErrorAttribute);
            request.getSession().removeAttribute(USER_EXISTS_ERROR_ATTRIBUTE);
        }

        return new CommandResult(PagePath.SIGN_UP_PAGE, CommandType.FORWARD);
    }
}
