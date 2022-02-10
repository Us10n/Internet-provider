package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class LocaleChangeCommand implements Command {
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String currentPage = (String) request.getSession().getAttribute(CURRENT_PAGE_ATTRIBUTE);
        String page = currentPage != null ? currentPage : PagePath.ERROR_INTERNAL_PAGE;
        CommandType commandType = currentPage != null ? CommandType.REDIRECT : CommandType.FORWARD;

        return new CommandResult(page, commandType);
    }
}
