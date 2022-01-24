package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ShowBalanceRechargePageCommand implements Command {
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String CURRENT_PAGE = "?command=rechargeBalancePage";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE);
        return new CommandResult(PagePath.BALANCE_RECHARGE_PAGE, CommandType.FORWARD);
    }
}
