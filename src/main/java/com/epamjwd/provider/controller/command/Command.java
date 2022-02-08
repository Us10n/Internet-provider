package com.epamjwd.provider.controller.command;

import com.epamjwd.provider.controller.CommandResult;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command is implemented in every Command class.
 */
public interface Command {
    /**
     * Execute command and returns the result.
     *
     * @param request the request
     * @return the command result
     */
    CommandResult execute(HttpServletRequest request);
}
