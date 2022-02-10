package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class TariffAddCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String TARIFF_NAME_PARAMETER = "tariffName";
    private static final String TARIFF_INTERNET_SPEED_PARAMETER = "tariffInternetSpeed";
    private static final String TARIFF_PRICE_PARAMETER = "tariffPrice";
    private static final String TARIFF_IMAGE_PARAMETER = "tariffImage";
    private static final String TARIFF_DESCRIPTION_PARAMETER = "tariffDescription";
    private static final String ADD_ERROR_ATTRIBUTE = "addError";
    private static final String EXISTS_ERROR_ATTRIBUTE = "existsError";
    private static final String TARIFFS_PANEL_PAGE = "?command=tariffsPanel";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String name = request.getParameter(TARIFF_NAME_PARAMETER);
        String internetSpeed = request.getParameter(TARIFF_INTERNET_SPEED_PARAMETER);
        String price = request.getParameter(TARIFF_PRICE_PARAMETER);
        String image = request.getParameter(TARIFF_IMAGE_PARAMETER);
        String description = request.getParameter(TARIFF_DESCRIPTION_PARAMETER);

        TariffService tariffService = ServiceHolder.getInstance().getTariffService();
        String page;
        CommandType commandType;
        try {
            boolean creationStatus = tariffService.createNewTariff(name, internetSpeed, price, image, description);
            if (!creationStatus) {
                Optional<Tariff> tariffOptional = tariffService.findTariffByName(name);
                if (tariffOptional.isEmpty()) {
                    request.setAttribute(ADD_ERROR_ATTRIBUTE, true);
                } else {
                    request.setAttribute(EXISTS_ERROR_ATTRIBUTE, true);
                }
            }
            page = creationStatus ? TARIFFS_PANEL_PAGE : PagePath.TARIFF_ADD_PAGE;
            commandType = creationStatus ? CommandType.REDIRECT : CommandType.FORWARD;
        } catch (ServiceException e) {
            logger.error("Create new tariff error", e);
            page = PagePath.ERROR_INTERNAL_PAGE;
            commandType = CommandType.FORWARD;
        }
        return new CommandResult(page, commandType);
    }
}
