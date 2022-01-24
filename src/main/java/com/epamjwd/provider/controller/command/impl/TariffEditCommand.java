package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class TariffEditCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NAME_PARAMETER = "tariffName";
    private static final String INTERNET_SPEED_PARAMETER = "tariffInternetSpeed";
    private static final String PRICE_PARAMETER = "tariffPrice";
    private static final String IMAGE_PARAMETER = "tariffImage";
    private static final String STATUS_PARAMETER = "tariffStatus";
    private static final String SPECIAL_OFFER_TITLE_PARAMETER = "specialOfferTitle";
    private static final String DESCRIPTION_PARAMETER = "tariffDescription";
    private static final String TARIFFS_PAGE = "?command=tariffs";
    private static final String EDIT_ERROR_ATTRIBUTE = "editError";
    private static final String NONE = "NONE";


    @Override
    public CommandResult execute(HttpServletRequest request) {

        String name = request.getParameter(NAME_PARAMETER);
        String internetSpeed = request.getParameter(INTERNET_SPEED_PARAMETER);
        String price = request.getParameter(PRICE_PARAMETER);
        String image = request.getParameter(IMAGE_PARAMETER);
        String status = request.getParameter(STATUS_PARAMETER);
        String specialOfferTitle = request.getParameter(SPECIAL_OFFER_TITLE_PARAMETER);
        String description = request.getParameter(DESCRIPTION_PARAMETER);

        if (specialOfferTitle != null && specialOfferTitle.equals(NONE)) {
            specialOfferTitle = null;
        }

        TariffService tariffService = ServiceHolder.getInstance().getTariffService();

        try {
            boolean updateStatus = tariffService.updateTariff(name, internetSpeed, price, image, description, status, specialOfferTitle);
            String page;
            CommandType commandType;
            if (updateStatus) {

                page = TARIFFS_PAGE;
                commandType = CommandType.REDIRECT;
            } else {
                page = PagePath.TARIFF_EDIT_PAGE;
                commandType = CommandType.FORWARD;
                request.setAttribute(EDIT_ERROR_ATTRIBUTE, true);
            }
            return new CommandResult(page, commandType);
        } catch (ServiceException e) {
            logger.error("Update tariff error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
    }
}
