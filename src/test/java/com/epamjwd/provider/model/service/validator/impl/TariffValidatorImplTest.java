package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.TariffValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

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
    public void testIsImageValidRegex() {
        String imageName = "default.png";
        TariffValidator tariffValidator = TariffValidatorImpl.getInstance();
        boolean actual = tariffValidator.isImageNameValid(imageName);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testIsImageValidFileExists() {
        String baseUrl = "src/main/webapp/static/images/tariff/";
        String imageName = "default.png";
        boolean actual = new File(baseUrl+imageName).isFile();
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testIsPriceValid() {
    }
}