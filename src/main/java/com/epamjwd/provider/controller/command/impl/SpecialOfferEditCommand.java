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

public class SpecialOfferEditCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String SPECIAL_OFFER_TITLE_PARAMETER = "offerTitle";
    private static final String SPECIAL_OFFER_START_DATE_PARAMETER = "offerStartDate";
    private static final String SPECIAL_OFFER_EXPIRATION_DATE_PARAMETER = "offerExpirationDate";
    private static final String SPECIAL_OFFER_DISCOUNT_PARAMETER = "discount";
    private static final String SPECIAL_OFFER_IMAGE_PARAMETER = "offerImage";
    private static final String SPECIAL_OFFER_DESCRIPTION_PARAMETER = "offerDescription";
    private static final String SPECIAL_OFFER_EDIT_ERROR_ATTRIBUTE = "editError";
    private static final String SPECIAL_OFFER_LIST_PAGE = "?command=promotions";


    @Override
    public CommandResult execute(HttpServletRequest request) {

        String title = request.getParameter(SPECIAL_OFFER_TITLE_PARAMETER);
        String startDate = request.getParameter(SPECIAL_OFFER_START_DATE_PARAMETER);
        String expirationDate = request.getParameter(SPECIAL_OFFER_EXPIRATION_DATE_PARAMETER);
        String discount = request.getParameter(SPECIAL_OFFER_DISCOUNT_PARAMETER);
        String image = request.getParameter(SPECIAL_OFFER_IMAGE_PARAMETER);
        String description = request.getParameter(SPECIAL_OFFER_DESCRIPTION_PARAMETER);

        SpecialOfferService specialOfferService = ServiceHolder.getInstance().getSpecialOfferService();
        String page;
        CommandType commandType;
        try {
            boolean updateStatus = specialOfferService.updateSpecialOfferByTitle(title, startDate, expirationDate, discount, image, description);
            page = updateStatus ? SPECIAL_OFFER_LIST_PAGE : PagePath.PROMOTION_EDIT_PAGE;
            commandType = updateStatus ? CommandType.REDIRECT : CommandType.FORWARD;
            request.setAttribute(SPECIAL_OFFER_EDIT_ERROR_ATTRIBUTE, !updateStatus);
        } catch (ServiceException e) {
            logger.error("Special offer update error", e);
            page = PagePath.ERROR_INTERNAL_PAGE;
            commandType = CommandType.FORWARD;
        }
        return new CommandResult(page, commandType);

    }
}
