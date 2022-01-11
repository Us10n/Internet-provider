package com.epamjwd.provider.model.dao.mapper;

import com.epamjwd.provider.model.dao.mapper.imp.*;
import com.epamjwd.provider.model.entity.*;

public class RowMapperFactory {
    private static RowMapperFactory instance;
    private final RowMapper<User> userRowMapper = new UserMapper();
    private final RowMapper<Role> roleRowMapper = new RoleMapper();
    private final RowMapper<BankAccount> bankAccountRowMapper = new BankAccountMapper();
    private final RowMapper<Tariff> tariffRowMapper = new TariffMapper();
    private final RowMapper<SpecialOffer> specialOfferRowMapper = new SpecialOfferMapper();
    private final RowMapper<Feedback> feedbackRowMapper = new FeedbackMapper();

    private RowMapperFactory() {
    }

    public static RowMapperFactory getInstance() {
        if (instance == null) {
            instance = new RowMapperFactory();
        }
        return instance;
    }

    public RowMapper getUserRowMapper() {
        return userRowMapper;
    }

    public RowMapper getRoleRowMapper() {
        return roleRowMapper;
    }

    public RowMapper getBankAccountRowMapper() {
        return bankAccountRowMapper;
    }

    public RowMapper getTariffRowMapper() {
        return tariffRowMapper;
    }

    public RowMapper getSpecialOfferRowMapper() {
        return specialOfferRowMapper;
    }

    public RowMapper getFeedbackRowMapper() {
        return feedbackRowMapper;
    }
}
