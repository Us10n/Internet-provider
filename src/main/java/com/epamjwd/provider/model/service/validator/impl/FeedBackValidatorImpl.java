package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.FeedBackValidator;

public class FeedBackValidatorImpl implements FeedBackValidator {

    private static FeedBackValidatorImpl instance;

    private FeedBackValidatorImpl(){
    }

    public static FeedBackValidator getInstance() {
        if(instance==null){
            instance=new FeedBackValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isTextBodyValid(String textBody) {
        return textBody.length() <= 2048;
    }
}
