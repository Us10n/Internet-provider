package com.epamjwd.provider.model.service.validator;

public interface TariffValidator {
    boolean isNameValid(String name);
    boolean isDescriptionValid(String description);
    boolean isInternetSpeedValid(String speed);
    boolean isImageNameValid(String image);
    boolean isPriceValid(String price);

}
