package com.epamjwd.provider.model.service.validator;

/**
 * The interface Bank account validator.
 */
public interface BankAccountValidator {
    /**
     * Validate requested money amount.
     *
     * @param requestedAmount the requested amount
     * @return the boolean
     */
    boolean isRequestedMoneyAmountValid(String requestedAmount);
}
