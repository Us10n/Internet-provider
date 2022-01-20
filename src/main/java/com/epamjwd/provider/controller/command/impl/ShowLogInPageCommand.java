package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;


public class ShowLogInPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().setAttribute("currentPage","?command=login");
        return new CommandResult(PagePath.LOG_IN_PAGE, CommandType.FORWARD);
    }
}
