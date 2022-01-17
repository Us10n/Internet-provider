package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.CommandType;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class MainCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.MAIN_PAGE, CommandType.Forward);
    }
}
