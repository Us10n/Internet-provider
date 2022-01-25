package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Feedback;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.service.FeedbackService;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowTariffSinglePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String TARIFF_NAME_PARAMETER = "name";
    private static final String TARIFF_ATTRIBUTE = "tariff";
    private static final String FEEDBACK_LIST_ATTRIBUTE = "feedbacks";
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private static final String CURRENT_PAGE = "?command=tariff&name=";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String tariffName = request.getParameter(TARIFF_NAME_PARAMETER);

        TariffService tariffService = ServiceHolder.getInstance().getTariffService();
        FeedbackService feedbackService = ServiceHolder.getInstance().getFeedbackService();
        Optional<Tariff> tariffOptional = Optional.empty();
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            String page;
            tariffOptional = tariffService.findTariffByName(tariffName);
            if (tariffOptional.isEmpty()) {
                page = PagePath.ERROR_NOT_FOUND_PAGE;
            } else {
                feedbackList = feedbackService.findFeedbacksByTariffId(tariffOptional.get().getId());
                page = PagePath.SINGLE_TARIFF_PAGE;
                request.setAttribute(FEEDBACK_LIST_ATTRIBUTE, feedbackList);
                request.setAttribute(TARIFF_ATTRIBUTE, tariffOptional.get());
                request.getSession().setAttribute(CURRENT_PAGE_ATTRIBUTE, CURRENT_PAGE + tariffName);
            }
            return new CommandResult(page, CommandType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Tariff service error");
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }
    }
}
