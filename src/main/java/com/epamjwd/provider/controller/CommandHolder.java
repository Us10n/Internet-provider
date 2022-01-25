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
        commandMap.put(CommandName.INTERNAL_ERROR, new DefaultCommand());
        commandMap.put(CommandName.HOME, new ShowHomePageCommand());
        commandMap.put(CommandName.LOG_IN, new ShowLogInPageCommand());
        commandMap.put(CommandName.SIGN_UP, new ShowSignUpPageCommand());
        commandMap.put(CommandName.TARIFFS, new ShowTariffListPageCommand());
        commandMap.put(CommandName.PROMOTIONS, new ShowSpecialOfferPageCommand());
        commandMap.put(CommandName.SINGLE_TARIFF, new ShowTariffSinglePageCommand());
        commandMap.put(CommandName.CHANGE_LOCALE, new LocaleChangeCommand());
        commandMap.put(CommandName.ABOUT_US, new ShowAboutUsCommand());
        commandMap.put(CommandName.SIGN_UP_USER, new UserSignUpCommand());
        commandMap.put(CommandName.LOG_IN_USER, new UserLogInCommand());
        commandMap.put(CommandName.VERIFY, new ShowVerificationPageCommand());
        commandMap.put(CommandName.PROFILE, new ShowProfilePageCommand());
        commandMap.put(CommandName.LOG_OUT_USER, new UserLogOutCommand());
        commandMap.put(CommandName.FORBIDDEN_ERROR, new ShowForbiddenPageCommand());
        commandMap.put(CommandName.PAGE_NOT_FOUND_ERROR, new ShowPageNotFoundCommand());
        commandMap.put(CommandName.TARIFF_ADD_PAGE, new ShowTariffAddPageCommand());
        commandMap.put(CommandName.TARIFF_ADD, new TariffAddCommand());
        commandMap.put(CommandName.TARIFF_EDIT_PAGE, new ShowTariffEditPageCommand());
        commandMap.put(CommandName.TARIFF_EDIT, new TariffEditCommand());
        commandMap.put(CommandName.BALANCE_RECHARGE_PAGE, new ShowBalanceRechargePageCommand());
        commandMap.put(CommandName.BALANCE_RECHARGE, new BalanceRechargeCommand());
        commandMap.put(CommandName.PROFILE_EDIT, new ProfileEditCommand());
        commandMap.put(CommandName.PROMOTION_EDIT_PAGE, new ShowSpecialOfferEditPageCommand());
        commandMap.put(CommandName.PROMOTION_EDIT, new SpecialOfferEditCommand());
        commandMap.put(CommandName.PROMOTION_DELETE, new SpecialOfferDeleteCommand());
        commandMap.put(CommandName.PROMOTION_ADD_PAGE, new ShowSpecialOfferAddPageCommand());
        commandMap.put(CommandName.PROMOTION_ADD, new SpecialOfferAddCommand());
        commandMap.put(CommandName.USERS_PANEL, new ShowUserPanelPageCommand());
        commandMap.put(CommandName.USER_ACTION, new UserActionCommand());
        commandMap.put(CommandName.TARIFF_ACTION, new TariffActionCommand());
        commandMap.put(CommandName.FEEDBACK_ADD, new UserCommentCommand());
    }

    public static CommandHolder getInstance() {
        return InstanceHolder.instance;
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName) == null ?
                commandMap.get(CommandName.PAGE_NOT_FOUND_ERROR) : commandMap.get(commandName);
    }
}
