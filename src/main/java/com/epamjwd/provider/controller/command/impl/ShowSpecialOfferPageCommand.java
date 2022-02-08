package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.SpecialOfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowSpecialOfferPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String CURRENT_PAGE = "?command=promotions";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        SpecialOfferService specialOfferService = ServiceHolder.getInstance().getSpecialOfferService();
        List<SpecialOffer> specialOfferList = new ArrayList<>();
        try {
            specialOfferList = specialOfferService.findAllPromotions();
        } catch (ServiceException e) {
            logger.error("Special offers service error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }

        request.setAttribute("offers", specialOfferList);
        request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE);
        return new CommandResult(PagePath.PROMOTION_LIST_PAGE, CommandType.FORWARD);
    }
}
