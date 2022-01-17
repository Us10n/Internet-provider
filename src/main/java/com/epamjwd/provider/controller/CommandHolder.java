package com.epamjwd.provider.controller;

import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.impl.ErrorCommand;
import com.epamjwd.provider.controller.command.impl.LongInCommand;
import com.epamjwd.provider.controller.command.impl.MainCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {

    private static final Map<String, Command> commandMap = new HashMap<>();
    private static CommandHolder instance;

    public CommandHolder() {
        commandMap.put(CommandName.COMMAND_ERROR, new ErrorCommand());
        commandMap.put(CommandName.COMMAND_LOG_IN, new LongInCommand());
        commandMap.put(CommandName.COMMAND_MAIN, new MainCommand());
    }

    public static CommandHolder getInstance() {
        if (instance == null) {
            instance = new CommandHolder();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName) == null ?
                commandMap.get(CommandName.COMMAND_ERROR) : commandMap.get(commandName);
    }
}
