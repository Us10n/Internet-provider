package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.model.dao.impl.*;

public class DaoHolder {
    private BankAccountDao bankAccountDao = new BankAccountDaoImpl();
    private FeedbackDao feedBackDao = new FeedbackDaoImpl();
    private SpecialOfferDao specialOfferDao=new SpecialOfferDaoImpl();
    private TariffDao tariffDao=new TariffDaoImpl();
    private UserDao userDao=new UserDaoImpl();
    private static DaoHolder instance;

    private DaoHolder(){
    }

    public static DaoHolder getInstance(){
        if(instance==null){
            instance=new DaoHolder();
        }
        return instance;
    }

    public BankAccountDao getBankAccountDao(){
        return bankAccountDao;
    }

    public FeedbackDao getFeedBackDao() {
        return feedBackDao;
    }

    public SpecialOfferDao getSpecialOfferDao() {
        return specialOfferDao;
    }

    public TariffDao getTariffDao() {
        return tariffDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
