package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;

import javax.servlet.http.HttpServletRequest;

public class UserLogOutCommand implements Command {
    private static final String USER_ATTRIBUTE = "user";
    private static final String LOGIN_PAGE = "?command=login";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        request.getSession().removeAttribute(USER_ATTRIBUTE);
        return new CommandResult(LOGIN_PAGE, CommandType.REDIRECT);
    }
}
