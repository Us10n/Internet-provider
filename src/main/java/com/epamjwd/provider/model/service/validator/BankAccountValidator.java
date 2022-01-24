package com.epamjwd.provider.model.service.validator;

public interface BankAccountValidator {
    boolean isRequestedMoneyAmountValid(String requestedAmount);
}
