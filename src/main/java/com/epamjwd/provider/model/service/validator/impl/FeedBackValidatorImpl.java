package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.FeedBackValidator;

public class FeedBackValidatorImpl implements FeedBackValidator {
    @Override
    public boolean isTextBodyValid(String textBody) {
        return textBody.length() <= 2048;
    }
}
