package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.CommandType;
import com.epamjwd.provider.controller.command.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ShowVerificationPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String TOKEN_PARAMETER = "token";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String token = request.getParameter(TOKEN_PARAMETER);
        UserService userService = ServiceHolder.getInstance().getUserService();
        String page;
        try {
            boolean verificationStatus = userService.verifyUser(token);
            page = verificationStatus ? PagePath.VERIFICATION_PAGE : PagePath.ERROR_NOT_FOUND_PAGE;
        } catch (ServiceException e) {
            logger.error("Verification error", e);
            page = PagePath.ERROR_INTERNAL_PAGE;
        }

        return new CommandResult(page, CommandType.FORWARD);
    }
}
