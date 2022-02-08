package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandName;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowTariffListPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NAME_SORT_PARAMETER = "name";
    private static final String PRICE_SORT_PARAMETER = "price";
    private static final String RATING_SORT_PARAMETER = "rating";
    private static final String SPEED_SORT_PARAMETER = "speed";
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String TARIFF_LIST_ATTRIBUTE = "tariffs";
    private static final String CURRENT_PAGE = "?command=tariffs&sort=";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        List<Tariff> tariffList = new ArrayList<>();
        TariffService tariffService = ServiceHolder.getInstance().getTariffService();

        String sortParameter = request.getParameter(CommandName.SORT) == null ?
                NAME_SORT_PARAMETER : request.getParameter(CommandName.SORT);
        try {
            tariffList = switch (sortParameter) {
                case PRICE_SORT_PARAMETER -> tariffService.findTariffsAndSortByPrice();
                case RATING_SORT_PARAMETER -> tariffService.findTariffsAndSortByRating();
                case SPEED_SORT_PARAMETER -> tariffService.findTariffsAndSortBySpeed();
                default -> tariffService.findTariffsAndSortByName();
            };
        } catch (ServiceException e) {
            logger.error("Find and sort tariffs error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
        request.setAttribute(TARIFF_LIST_ATTRIBUTE, tariffList);
        request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE + sortParameter);
        return new CommandResult(PagePath.TARIFF_LIST_PAGE, CommandType.FORWARD);
    }
}
