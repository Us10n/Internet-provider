package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.SpecialOfferService;
import com.epamjwd.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ShowTariffEditPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String TARIFF_ID_PARAMETER = "tariffId";
    private static final String TARIFF_ATTRIBUTE = "tariff";
    private static final String SPECIAL_OFFERS_ATTRIBUTE = "specialOffers";
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String CURRENT_PAGE = "?command=tariffEditPage&tariffId=";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String tariffId = request.getParameter(TARIFF_ID_PARAMETER);

        TariffService tariffService = ServiceHolder.getInstance().getTariffService();
        SpecialOfferService specialOfferService = ServiceHolder.getInstance().getSpecialOfferService();
        String page;
        try {
            Optional<Tariff> tariffOptional = tariffService.findTariffById(tariffId);
            List<SpecialOffer> specialOfferList = specialOfferService.findAllPromotions();
            if (tariffOptional.isPresent()) {
                request.setAttribute(TARIFF_ATTRIBUTE, tariffOptional.get());
                request.setAttribute(SPECIAL_OFFERS_ATTRIBUTE, specialOfferList);
                request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE + tariffId);
                page = PagePath.TARIFF_EDIT_PAGE;
            } else {
                page = PagePath.ERROR_NOT_FOUND_PAGE;
            }
        } catch (ServiceException e) {
            logger.error("Tariff find by name error", e);
            page=PagePath.ERROR_INTERNAL_PAGE;
        }
        return new CommandResult(page, CommandType.FORWARD);
    }
}
