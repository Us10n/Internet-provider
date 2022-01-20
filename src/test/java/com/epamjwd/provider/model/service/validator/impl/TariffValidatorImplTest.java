package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.TariffValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TariffValidatorImplTest {

    @Test
    public void testIsNameValid() {
    }

    @Test
    public void testIsDescriptionValid() {
    }

    @Test
    public void testIsInternetSpeedValid() {
    }

    @Test
    public void testIsImageUrlValid() {
        String imageUri="tariff/start";
        TariffValidator tariffValidator=new TariffValidatorImpl();
        boolean actual= tariffValidator.isImageUrlValid(imageUri);
        boolean expected=true;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testIsPriceValid() {
    }
}