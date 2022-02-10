package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.FeedBackValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FeedBackValidatorImplTest {
    FeedBackValidator feedBackValidator=FeedBackValidatorImpl.getInstance();

    @Test
    public void testIsTextBodyValid() {
        String textBody="This is feedback body text";
        boolean actual= feedBackValidator.isTextBodyValid(textBody);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsRatingValid() {
        String rating="9";
        boolean actual= feedBackValidator.isRatingValid(rating);
        Assert.assertTrue(actual);
    }
}