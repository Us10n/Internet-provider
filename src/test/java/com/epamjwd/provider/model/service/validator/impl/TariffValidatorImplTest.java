package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.TariffValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class TariffValidatorImplTest {
    TariffValidator tariffValidator = TariffValidatorImpl.getInstance();

    @Test
    public void testIsNameValid() {
        String name="This is name";
        boolean actual=tariffValidator.isNameValid(name);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDescriptionValid() {
        String description="This is description";
        boolean actual= tariffValidator.isDescriptionValid(description);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsInternetSpeedValid() {
        String internetSpeed="1000";
        boolean actual= tariffValidator.isInternetSpeedValid(internetSpeed);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsImageValidRegex() {
        String imageName = "default.png";
        boolean actual = tariffValidator.isImageNameValid(imageName);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsImageValidFile() {
        String imageName = "default.png";
        boolean actual = tariffValidator.isImageNameValid(imageName);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsPriceValid() {
        String price="123.12";
        boolean actual= tariffValidator.isPriceValid(price);
        Assert.assertTrue(actual);
    }
}