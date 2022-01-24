package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ShowLogInPageCommand implements Command {
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String CURRENT_PAGE = "?command=login";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE);
        return new CommandResult(PagePath.LOG_IN_PAGE, CommandType.FORWARD);
    }
}
