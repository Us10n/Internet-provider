package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.TariffValidator;

import java.io.File;

public class TariffValidatorImpl implements TariffValidator {

    private static final String VALID_NAME_REGEX = "^[A-Za-zА-Яа-я0-9]{1,15}$";
    private static final String VALID_IMAGE_URL_REGEX = "^tariff/[a-zA-Z.]{1,38}$";
    private static final String VALID_DOUBLE_REGEX = "^\\d+.\\d+$";

    @Override
    public boolean isNameValid(String name) {
        return name.matches(VALID_NAME_REGEX);
    }

    @Override
    public boolean isDescriptionValid(String description) {
        return description.length() <= 2048;
    }

    @Override
    public boolean isInternetSpeedValid(String speed) {
        return speed.matches(VALID_DOUBLE_REGEX) && speed.length() <= 10;
    }

    @Override
    public boolean isImageUrlValid(String url) {
        String baseUrl = "src/main/webapp/static/images/";
        return new File(baseUrl + url).isFile() &&
                url.matches(VALID_IMAGE_URL_REGEX);
    }

    @Override
    public boolean isPriceValid(String price) {
        return price.matches(VALID_DOUBLE_REGEX) && price.length() < 13;
    }
}
