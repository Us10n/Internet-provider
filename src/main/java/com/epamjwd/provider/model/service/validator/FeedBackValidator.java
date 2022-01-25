package com.epamjwd.provider.model.service.validator;

public interface FeedBackValidator {
    boolean isTextBodyValid(String textBody);
    boolean isRatingValid(String rating);
}
