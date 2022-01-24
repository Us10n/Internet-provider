package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;
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
    private static final String TARIFFS_PAGE = "?command=tariffs";


    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String rechargeAmount = request.getParameter(RECHARGE_AMOUNT_PARAMETER);
        Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);

        BankAccountService bankAccountService = ServiceHolder.getInstance().getBankAccountService();

        try {
            BankAccount bankAccount = bankAccountService.findBankAccountByUserId(userId);
            boolean rechargeStatus = bankAccountService.rechargeBalance(bankAccount, rechargeAmount);
            String page;
            CommandType commandType;
            if (rechargeStatus) {
                page = TARIFFS_PAGE;
                commandType = CommandType.REDIRECT;
            } else {
                page = PagePath.BALANCE_RECHARGE_PAGE;
                commandType = CommandType.FORWARD;
                request.setAttribute(RECHARGE_ERROR_ATTRIBUTE, true);
            }
            return new CommandResult(page, commandType);
        } catch (ServiceException e) {
            logger.error("Recharge balance error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
    }
}
