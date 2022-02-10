package com.epamjwd.provider.model.service.validator.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BankAccountValidatorImplTest {

    @Test
    public void testIsRequestedMoneyAmountValid() {
        String requestedMoney="100.10";
        boolean actual=BankAccountValidatorImpl.getInstance().isRequestedMoneyAmountValid(requestedMoney);
        Assert.assertTrue(actual);
    }
}