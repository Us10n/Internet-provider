package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.SpecialOfferValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SpecialOfferValidatorImplTest {
    SpecialOfferValidator specialOfferValidator = SpecialOfferValidatorImpl.getInstance();

    @Test
    public void testIsTitleValid() {
        String title = "This is title";
        boolean actual = specialOfferValidator.isTitleValid(title);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDescriptionValid() {
        String description = "This is description";
        boolean actual = specialOfferValidator.isDescriptionValid(description);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDateValid() {
        String date = "1111-11-28";
        boolean actual = specialOfferValidator.isDateValid(date);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsDiscountValid() {
        String discount = "100";
        boolean actual = specialOfferValidator.isDiscountValid(discount);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsImageNameValid() {
        String imageName = "default.png";
        boolean actual = specialOfferValidator.isImageNameValid(imageName);
        Assert.assertTrue(actual);
    }
}