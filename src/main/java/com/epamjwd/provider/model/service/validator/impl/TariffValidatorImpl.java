package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.TariffValidator;

import java.io.File;

public class TariffValidatorImpl implements TariffValidator {

    private static final String VALID_TITLE_REGEX = "^[A-Za-zА-Я а-я0-9]{1,15}$";
    private static final String VALID_IMAGE_REGEX = "^[\\w_]+\\.[A-Za-z]{3}$";
    private static final String VALID_SPEED_REGEX = "^\\d+$";
    private static final String VALID_PRICE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    private static final String VALID_STATUS_REGEX = "^ACTIVE|ARCHIVE|DEACTIVATED$";
    private static TariffValidatorImpl instance;

    private TariffValidatorImpl() {
    }

    public static TariffValidator getInstance() {
        if (instance == null) {
            instance = new TariffValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isNameValid(String name) {
        return name != null && name.matches(VALID_TITLE_REGEX);
    }

    @Override
    public boolean isDescriptionValid(String description) {
        return description != null && description.length() <= 2048;
    }

    @Override
    public boolean isInternetSpeedValid(String speed) {
        return speed != null && speed.matches(VALID_SPEED_REGEX) && speed.length() < 5;
    }

    @Override
    public boolean isImageNameValid(String name) {
        String baseUrl = "src/main/webapp/static/images/tariff/";
        return name != null && name.matches(VALID_IMAGE_REGEX) &&
                name.length() <= 45;
    }

    @Override
    public boolean isPriceValid(String price) {
        return price != null && price.matches(VALID_PRICE_REGEX) && price.length() <= 11;
    }

    @Override
    public boolean isTariffStatusValid(String status) {
        return status != null && status.matches(VALID_STATUS_REGEX);
    }
}
