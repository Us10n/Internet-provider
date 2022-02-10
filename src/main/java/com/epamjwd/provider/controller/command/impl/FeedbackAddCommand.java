package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.service.FeedbackService;
import com.epamjwd.provider.model.service.ServiceHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class FeedbackAddCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String TARIFF_ID_PARAMETER = "tariffId";
    private static final String FEEDBACK_BODY_PARAMETER = "feedbackBody";
    private static final String FEEDBACK_RATING_PARAMETER = "rating";
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String CURRENT_PAGE_ATTRIBUTE = "currentPage";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        Long userId = (Long) request.getSession().getAttribute(USER_ID_ATTRIBUTE);
        String tariffId = request.getParameter(TARIFF_ID_PARAMETER);
        String rating = request.getParameter(FEEDBACK_RATING_PARAMETER);
        String feedbackBody = request.getParameter(FEEDBACK_BODY_PARAMETER);
        String currentPage = (String) request.getSession().getAttribute(CURRENT_PAGE_ATTRIBUTE);

        FeedbackService feedbackService = ServiceHolder.getInstance().getFeedbackService();
        String page;
        CommandType commandType;
        try {
            feedbackService.createNewFeedback(userId, tariffId, rating, feedbackBody);
            page=currentPage;
            commandType=CommandType.REDIRECT;
        } catch (ServiceException e) {
            logger.error("Feedback create error", e);
            page=PagePath.ERROR_INTERNAL_PAGE;
            commandType=CommandType.FORWARD;
        }
        return new CommandResult(page, commandType);
    }
}
