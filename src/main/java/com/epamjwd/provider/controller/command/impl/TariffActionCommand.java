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

public class TariffActionCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ACTION_PARAMETER = "action";
    private static final String ACTION_SUBSCRIBE = "subscribe";
    private static final String ACTION_UNSUBSCRIBE = "unsubscribe";
    private static final String TARIFF_ID_PARAMETER = "tariffId";
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String PROFILE_PAGE = "?command=profile";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String action = request.getParameter(ACTION_PARAMETER);
        if (action != null) {
            try {
                String tariffId = request.getParameter(TARIFF_ID_PARAMETER);
                Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);

                BankAccountService bankAccountService = ServiceHolder.getInstance().getBankAccountService();
                switch (action) {
                    case ACTION_SUBSCRIBE -> bankAccountService.subscribeTariff(userId, tariffId);
                    case ACTION_UNSUBSCRIBE -> bankAccountService.unsubscribeTariff(userId);
                    default -> throw new ServiceException("Can not find action " + action);
                }
                return new CommandResult(PROFILE_PAGE, CommandType.REDIRECT);
            } catch (ServiceException e) {
                logger.error("Tariff subscribe/unsubscribe error", e);
                return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
            }
        }
        return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
    }

}

