package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.TariffService;
import com.epamjwd.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowProfilePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String BANK_ACCOUNT_ATTRIBUTE = "bankAccount";
    private static final String TARIFF_NAME_PARAMETER = "tariffName";
    private static final String CURRENT_PAGE = "?command=profile";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        BankAccount bankAccount = (BankAccount) request.getSession().getAttribute(BANK_ACCOUNT_ATTRIBUTE);

        try {
            if (bankAccount.getTariffId().isPresent()) {
                TariffService tariffService = ServiceHolder.getInstance().getTariffService();
                Optional<Tariff> optionalTariff = tariffService.findTariffById(bankAccount.getTariffId().get());
                if (optionalTariff.isEmpty()) {
                    return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
                }
                String tariffName = optionalTariff.get().getName();
                request.setAttribute(TARIFF_NAME_PARAMETER, tariffName);
            }
        } catch (ServiceException e) {
            logger.error("Find tariff by id error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
        request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE);
        return new CommandResult(PagePath.PROFILE_PAGE, CommandType.FORWARD);
    }
}
