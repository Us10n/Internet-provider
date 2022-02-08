package com.epamjwd.provider.controller;

import com.epamjwd.provider.controller.command.CommandType;

public class CommandResult {
    private String page="";
    private CommandType commandType;

    public CommandResult() {
    }

    public CommandResult(String page, CommandType commandType) {
        this.page = page;
        this.commandType = commandType;
    }

    public String getPage() {
        return page;
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
