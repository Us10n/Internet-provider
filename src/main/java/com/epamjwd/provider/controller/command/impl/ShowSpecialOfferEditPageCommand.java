package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.SpecialOfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowSpecialOfferEditPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String CURRENT_PAGE = "?command=promotionEditPage&offerId=";
    private static final String SPECIAL_OFFER_ID_PARAMETER = "offerId";
    private static final String SPECIAL_OFFER_ATTRIBUTE = "specialOffer";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String offerId = request.getParameter(SPECIAL_OFFER_ID_PARAMETER);

        SpecialOfferService specialOfferService = ServiceHolder.getInstance().getSpecialOfferService();

        try {
            Optional<SpecialOffer> specialOfferOptional = specialOfferService.findById(offerId);
            String page;
            if (specialOfferOptional.isPresent()) {
                request.setAttribute(SPECIAL_OFFER_ATTRIBUTE, specialOfferOptional.get());
                request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE + offerId);
                page = PagePath.PROMOTION_EDIT_PAGE;
            } else {
                page = PagePath.ERROR_NOT_FOUND_PAGE;
            }
            return new CommandResult(page, CommandType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Special offer find by title error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
    }
}
