package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.service.BankAccountService;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.TariffService;
import com.epamjwd.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowProfilePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";

    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    private static final String USER_ROLE_ATTRIBUTE = "userRole";
    private static final String BANK_ACCOUNT_ID = "bankAccountId";

    private static final String USER_PARAMETER = "user";
    private static final String BANK_ACCOUNT_PARAMETER = "bankAccount";
    private static final String TARIFF_NAME_PARAMETER = "tariffName";
    private static final String CURRENT_PAGE = "?command=profile";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
        String email = (String) session.getAttribute(USER_EMAIL_ATTRIBUTE);

        try {
            BankAccountService bankAccountService = ServiceHolder.getInstance().getBankAccountService();
            BankAccount bankAccount = bankAccountService.findBankAccountByUserId(userId);
            TariffService tariffService = ServiceHolder.getInstance().getTariffService();

            if (bankAccount.getTariffId().isPresent()) {
                Optional<Tariff> optionalTariff = tariffService.findTariffById(bankAccount.getTariffId().get());
                String tariffName = optionalTariff.get().getName();
                request.setAttribute(TARIFF_NAME_PARAMETER, tariffName);
            }
            UserService userService = ServiceHolder.getInstance().getUserService();
            Optional<User> userOptional = userService.findUserByEmail(email);
            if (userOptional.isEmpty()) {
                return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
            }
            User user = userOptional.get();
            request.setAttribute(USER_PARAMETER, user);
            request.setAttribute(BANK_ACCOUNT_PARAMETER,bankAccount);
        } catch (ServiceException e) {
            logger.error("Find tariff by id error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
        request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE);
        return new CommandResult(PagePath.PROFILE_PAGE, CommandType.FORWARD);
    }
}
