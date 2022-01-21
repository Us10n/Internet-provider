package com.epamjwd.provider.model.service.validator.impl;

import com.epamjwd.provider.model.service.validator.SpecialOfferValidator;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SpecialOfferValidatorImpl implements SpecialOfferValidator {

    private static final String VALID_TITLE_REGEX = "^[A-Za-zА-Яа-я0-9]{1,44}$";
    private static final String VALID_DATE_REGEX = "^\\d{4}-(02-(0[1-9]|[12][0-9])|(0[469]|11)-(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))$";
    private static final String VALID_IMAGE_URL_REGEX = "^offer/[a-zA-Z.]{1,38}$";
    private static final String VALID_DISCOUNT_REGEX = "^[0-9][0-9]?$|^100$";
    private static SpecialOfferValidatorImpl instance;

    private SpecialOfferValidatorImpl() {
    }

    public static SpecialOfferValidator getInstance() {
        if (instance == null) {
            instance = new SpecialOfferValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isTitleValid(String title) {
        return title != null && title.matches(VALID_TITLE_REGEX);
    }

    @Override
    public boolean isDescriptionValid(String description) {
        return description != null && description.length() <= 2048;
    }

    @Override
    public boolean isDateValid(String date) {
        if (date == null || !date.matches(VALID_DATE_REGEX)) {
            return false;
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isDiscountValid(String discount) {
        return discount != null && discount.matches(VALID_DISCOUNT_REGEX);
    }

    @Override
    public boolean isImageUrlValid(String url) {
        String baseUrl = "src/main/webapp/static/images/";
        return url != null && new File(baseUrl + url).isFile() &&
                url.matches(VALID_IMAGE_URL_REGEX);
    }
}
