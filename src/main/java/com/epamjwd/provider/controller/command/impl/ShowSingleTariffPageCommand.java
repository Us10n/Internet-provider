package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowSingleTariffPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String TARIFF_NAME_PARAMETER = "name";
    private static final String TARIFF_ATTRIBUTE = "tariff";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String tariffName = request.getParameter(TARIFF_NAME_PARAMETER);

        Optional<Tariff> tariffOptional = Optional.empty();
        TariffService tariffService = ServiceHolder.getInstance().getTariffService();
        try {
            tariffOptional = tariffService.findTariffByName(tariffName);
        } catch (ServiceException e) {
            logger.error("Tariff service error");
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }

        if (tariffOptional.isEmpty()) {
            return new CommandResult(PagePath.ERROR_NOT_FOUND_PAGE, CommandType.FORWARD);
        } else {
            request.setAttribute(TARIFF_ATTRIBUTE, tariffOptional.get());
            request.getSession().setAttribute("currentPage", "?command=tariff&name=" + tariffName);
            return new CommandResult(PagePath.SINGLE_TARIFF_PAGE, CommandType.FORWARD);
        }
    }
}
