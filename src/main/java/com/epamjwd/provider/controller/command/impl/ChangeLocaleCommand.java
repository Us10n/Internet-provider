package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {

        String page = (String) request.getSession().getAttribute("currentPage");

        return page != null ?
                new CommandResult(page, CommandType.REDIRECT) :
                new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
    }
}
