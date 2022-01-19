package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ShowHomePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.HOME_PAGE, CommandType.FORWARD);
    }
}
