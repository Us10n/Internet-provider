package com.epamjwd.provider.service;

import com.epamjwd.provider.service.impl.TariffServiceImpl;

public final class ServiceHolder {
    private TariffService tariffService=new TariffServiceImpl();
    private static ServiceHolder instance;

    private ServiceHolder(){
    }

    public static ServiceHolder getInstance(){
        if(instance==null){
            instance=new ServiceHolder();
        }
        return instance;
    }

    public TariffService getTariffService() {
        return tariffService;
    }
}
