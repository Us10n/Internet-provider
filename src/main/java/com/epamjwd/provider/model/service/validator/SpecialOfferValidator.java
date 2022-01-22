package com.epamjwd.provider.model.service.validator;

public interface SpecialOfferValidator {
    boolean isTitleValid(String title);
    boolean isDescriptionValid(String description);
    boolean isDateValid(String date);
    boolean isDiscountValid(String discount);
    boolean isImageNameValid(String image);
}
