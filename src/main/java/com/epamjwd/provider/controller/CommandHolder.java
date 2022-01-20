package com.epamjwd.provider.controller;

import com.epamjwd.provider.controller.command.Command;
import com.epamjwd.provider.controller.command.constants.CommandName;
import com.epamjwd.provider.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {

    private static final Map<String, Command> commandMap = new HashMap<>();

    private static class InstanceHolder {
        static final CommandHolder instance = new CommandHolder();
    }

    public CommandHolder() {
        commandMap.put(CommandName.ERROR, new DefaultCommand());
        commandMap.put(CommandName.HOME, new ShowHomePageCommand());
        commandMap.put(CommandName.LOG_IN, new ShowLogInPageCommand());
        commandMap.put(CommandName.SIGN_UP, new ShowSignUpPageCommand());
        commandMap.put(CommandName.TARIFFS, new ShowTariffListPageCommand());
        commandMap.put(CommandName.PROMOTIONS, new ShowSpecialOfferPageCommand());
        commandMap.put(CommandName.SINGLE_TARIFF, new ShowSingleTariffPageCommand());
        commandMap.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
    }

    public static CommandHolder getInstance() {
        return InstanceHolder.instance;
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName) == null ?
                commandMap.get(CommandName.ERROR) : commandMap.get(commandName);
    }
}
