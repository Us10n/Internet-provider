package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.BankAccountValidator;

public class BankAccountValidatorImpl implements BankAccountValidator {
    private static final String VALID_MONEY_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";

    private static BankAccountValidatorImpl instance;

    public static BankAccountValidator getInstance() {
        if (instance == null) {
            instance = new BankAccountValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isRequestedMoneyAmountValid(String amount) {
        return amount != null && amount.matches(VALID_MONEY_REGEX) && amount.length() <= 11;
    }
}
