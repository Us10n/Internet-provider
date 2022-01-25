package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.FeedBackValidator;

public class FeedBackValidatorImpl implements FeedBackValidator {

    private static final String RATING_VALID_REGEX = "^[1-9]$|^10$";

    private static FeedBackValidatorImpl instance;

    private FeedBackValidatorImpl() {
    }

    public static FeedBackValidator getInstance() {
        if (instance == null) {
            instance = new FeedBackValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isTextBodyValid(String textBody) {
        return textBody != null && textBody.length() <= 2048;
    }

    @Override
    public boolean isRatingValid(String rating) {
        return rating != null && rating.matches(RATING_VALID_REGEX);
    }
}
