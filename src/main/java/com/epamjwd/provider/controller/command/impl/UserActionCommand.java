package com.epamjwd.provider.controller.command.impl;

import com.epamjwd.provider.controller.CommandResult;
import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.PagePath;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.pool.ActiveUserPool;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.UserService;
import com.sun.mail.imap.protocol.UIDSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserActionCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String USER_ACTION_PARAMETER = "action";
    private static final String USER_EDITING_ID_PARAMETER = "userEditingId";
    private static final String ACTION_BAN_PARAMETER = "ban";
    private static final String ACTION_UNBAN_PARAMETER = "unban";
    private static final String ACTION_VERIFY_PARAMETER = "verify";
    private static final String ACTION_MAKE_ADMIN_PARAMETER = "makeAdmin";
    private static final String USER_UPDATE_ERROR_ATTRIBUTE = "updateError";
    private static final String USERS_PANEL_PAGE = "?command=usersPanel";

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String action = request.getParameter(USER_ACTION_PARAMETER);
        String userId = request.getParameter(USER_EDITING_ID_PARAMETER);
        if (action != null) {
            try {
                UserService userService = ServiceHolder.getInstance().getUserService();
                boolean userUpdateStatus = switch (action) {
                    case ACTION_BAN_PARAMETER -> userService.makeUserBanned(userId);
                    case ACTION_UNBAN_PARAMETER -> userService.makeUserUnbanned(userId);
                    case ACTION_VERIFY_PARAMETER -> userService.makeUserVerified(userId);
                    case ACTION_MAKE_ADMIN_PARAMETER -> userService.makeUserAdmin(userId);
                    default -> false;
                };
                request.setAttribute(USER_UPDATE_ERROR_ATTRIBUTE, !userUpdateStatus);
                String page = userUpdateStatus ? USERS_PANEL_PAGE : PagePath.ERROR_INTERNAL_PAGE;
                CommandType commandType = userUpdateStatus ? CommandType.REDIRECT : CommandType.FORWARD;
                return new CommandResult(page, commandType);
            } catch (ServiceException e) {
                logger.error("User update error", e);
                return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
            }
        }
        return new CommandResult(PagePath.ERROR_INTERNAL_PAGE, CommandType.FORWARD);
    }
}
