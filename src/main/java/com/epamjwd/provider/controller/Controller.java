package com.epamjwd.provider.controller;

import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandType;
import com.epamjwd.provider.controller.command.constants.CommandName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private static final String commandRedirectPath = "/provider/controller?command=";
    private static final String commandParameterString = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("get+ " + req.getQueryString());
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("post+ " + req.getQueryString());
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageParameter = request.getParameter(commandParameterString);
        if (pageParameter == null || pageParameter.equals("")) {
            response.sendRedirect(commandRedirectPath + CommandName.HOME);
        } else {
            Command command = CommandHolder.getInstance().getCommand(pageParameter);
            CommandResult commandResult = command.execute(request);
            if (commandResult.getCommandType() == CommandType.REDIRECT) {
                response.sendRedirect(commandResult.getPage());
            } else {
                request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
            }
        }
    }

}
