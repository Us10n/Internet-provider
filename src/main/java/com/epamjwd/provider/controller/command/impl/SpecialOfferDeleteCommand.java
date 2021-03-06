package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.SpecialOfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SpecialOfferDeleteCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String SPECIAL_OFFER_ID_PARAMETER = "offerId";
    private static final String SPECIAL_OFFERS_PAGE = "?command=promotions";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String offerId = request.getParameter(SPECIAL_OFFER_ID_PARAMETER);

        SpecialOfferService specialOfferService = ServiceHolder.getInstance().getSpecialOfferService();
        String page;
        CommandType commandType;
        try {
            boolean deletionStatus = specialOfferService.deleteSpecialOfferById(offerId);
            page = deletionStatus ? SPECIAL_OFFERS_PAGE : PagePath.ERROR_INTERNAL_PAGE;
            commandType = deletionStatus ? CommandType.REDIRECT : CommandType.FORWARD;
        } catch (ServiceException e) {
            logger.error("Special offer delete error");
            page = PagePath.ERROR_INTERNAL_PAGE;
            commandType = CommandType.FORWARD;
        }
        return new CommandResult(page, commandType);
    }
}
