package com.epamjwd.provider.model.service;

import com.epamjwd.provider.model.service.impl.BankAccountServiceImpl;
import com.epamjwd.provider.model.service.impl.SpecialOfferServiceImpl;
import com.epamjwd.provider.model.service.impl.TariffServiceImpl;
import com.epamjwd.provider.model.service.impl.UserServiceImpl;

public final class ServiceHolder {
    private TariffService tariffService = new TariffServiceImpl();
    private SpecialOfferService specialOfferService = new SpecialOfferServiceImpl();
    private UserService userService = new UserServiceImpl();
    private BankAccountService bankAccountService = new BankAccountServiceImpl();

    private static class InstanceHolder {
        static final ServiceHolder instance = new ServiceHolder();
    }

    private ServiceHolder() {
    }

    public static ServiceHolder getInstance() {
        return InstanceHolder.instance;
    }

    public TariffService getTariffService() {
        return tariffService;
    }

    public SpecialOfferService getSpecialOfferService() {
        return specialOfferService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BankAccountService getBankAccountService() {
        return bankAccountService;
    }
}
