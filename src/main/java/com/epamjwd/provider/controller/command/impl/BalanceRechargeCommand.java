package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.service.BankAccountService;
import com.epamjwd.provider.model.service.ServiceHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BalanceRechargeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String RECHARGE_AMOUNT_PARAMETER = "rechargeAmount";
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String RECHARGE_ERROR_ATTRIBUTE = "rechargeError";
    private static final String PROFILE_PAGE = "?command=profile";


    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String rechargeAmount = request.getParameter(RECHARGE_AMOUNT_PARAMETER);
        Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);

        BankAccountService bankAccountService = ServiceHolder.getInstance().getBankAccountService();
        CommandType commandType;
        String page;
        try {
            boolean rechargeStatus = bankAccountService.rechargeBalance(userId, rechargeAmount);
            request.setAttribute(RECHARGE_ERROR_ATTRIBUTE, !rechargeStatus);
            page = rechargeStatus ? PROFILE_PAGE : PagePath.BALANCE_RECHARGE_PAGE;
            commandType = rechargeStatus ? CommandType.REDIRECT : CommandType.FORWARD;
        } catch (ServiceException e) {
            logger.error("Recharge balance error", e);
            page=PagePath.ERROR_INTERNAL_PAGE;
            commandType=CommandType.FORWARD;
        }
        return new CommandResult(page, commandType);
    }
}
