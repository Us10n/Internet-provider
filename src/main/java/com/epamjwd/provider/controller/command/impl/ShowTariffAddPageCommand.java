package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ShowTariffAddPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().setAttribute("currentPage", "?command=tariffAddPage");
        return new CommandResult(PagePath.TARIFF_ADD_PAGE, CommandType.FORWARD);
    }
}
