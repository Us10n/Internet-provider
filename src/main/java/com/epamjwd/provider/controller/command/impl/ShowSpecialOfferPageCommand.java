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
import java.util.List;

public class ShowSpecialOfferPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String CURRENT_PAGE = "?command=promotions";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        SpecialOfferService specialOfferService = ServiceHolder.getInstance().getSpecialOfferService();
        List<SpecialOffer> specialOfferList;

        String page;
        try {
            specialOfferList = specialOfferService.findAllPromotions();
            request.setAttribute("offers", specialOfferList);
            request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE);
            page = PagePath.PROMOTION_LIST_PAGE;
        } catch (ServiceException e) {
            logger.error("Special offers service error", e);
            page = PagePath.ERROR_INTERNAL_PAGE;
        }
        return new CommandResult(page, CommandType.FORWARD);
    }
}
