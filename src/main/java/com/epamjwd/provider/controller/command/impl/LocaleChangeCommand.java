package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class LocaleChangeCommand implements Command {
    private static final String CURRENT_PAGE_ATTRIBUTE="currentPage";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String page = (String) request.getSession().getAttribute(CURRENT_PAGE_ATTRIBUTE);

        return page != null ?
                new CommandResult(page, CommandType.REDIRECT) :
                new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
    }
}
