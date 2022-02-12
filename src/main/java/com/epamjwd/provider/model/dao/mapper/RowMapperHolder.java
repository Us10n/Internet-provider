package com.epamjwd.provider.model.dao.mapper;

import com.epamjwd.provider.model.dao.mapper.imp.*;
import com.epamjwd.provider.model.entity.*;

public final class RowMapperHolder {
    private static RowMapperHolder instance;
    private final RowMapper<User> userRowMapper = new UserMapper();
    private final RowMapper<BankAccount> bankAccountRowMapper = new BankAccountMapper();
    private final RowMapper<Tariff> tariffRowMapper = new TariffMapper();
    private final RowMapper<SpecialOffer> specialOfferRowMapper = new SpecialOfferMapper();
    private final RowMapper<Feedback> feedbackRowMapper = new FeedbackMapper();

    private RowMapperHolder() {
    }

    public static RowMapperHolder getInstance() {
        if (instance == null) {
            instance = new RowMapperHolder();
        }
        return instance;
    }

    public RowMapper getUserRowMapper() {
        return userRowMapper;
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
