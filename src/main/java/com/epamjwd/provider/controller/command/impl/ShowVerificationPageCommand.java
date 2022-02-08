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
        try {
            boolean verificationStatus = userService.verifyUser(token);
            if (!verificationStatus) {
                return new CommandResult(PagePath.ERROR_NOT_FOUND_PAGE, CommandType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Verification error", e);
            return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
        }

        return new CommandResult(PagePath.VERIFICATION_PAGE, CommandType.FORWARD);
    }
}
