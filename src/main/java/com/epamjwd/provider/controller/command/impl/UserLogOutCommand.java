package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLogOutCommand implements Command {
    private static final String LOGIN_PAGE = "?command=login";
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    private static final String USER_ROLE_ATTRIBUTE = "userRole";
    private static final String USER_TARIFF_ID_ATTRIBUTE = "userTariffId";
    private static final String USER_STATUS_ATTRIBUTE = "userStatus";


    @Override
    public CommandResult execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute(USER_ID_ATTRIBUTE);
        session.removeAttribute(USER_EMAIL_ATTRIBUTE);
        session.removeAttribute(USER_ROLE_ATTRIBUTE);
        session.removeAttribute(USER_TARIFF_ID_ATTRIBUTE);
        session.removeAttribute(USER_STATUS_ATTRIBUTE);
        return new CommandResult(LOGIN_PAGE, CommandType.REDIRECT);
    }
}
