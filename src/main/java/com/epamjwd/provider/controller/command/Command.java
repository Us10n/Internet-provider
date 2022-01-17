package com.epamjwd.provider.controller.command;

import com.epamjwd.provider.controller.CommandResult;

import javax.servlet.http.HttpServletRequest;


public interface Command {
    CommandResult execute(HttpServletRequest request);
}
