package com.epamjwd.provider.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public final class ColumnName {

    public static final String ID = "id";

    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_STATUS = "status";
    public static final String USER_ROLE_ID = "roles_id";

    public static final String ROLE_NAME = "role";

    public static final String BANK_ACCOUNT_BALANCE = "balance";
    public static final String BANK_ACCOUNT_USER_ID = "user_id";
    public static final String BANK_ACCOUNT_TARIFF_ID = "tariffs_id";

    public static final String TARIFF_ID = "special_offers_id";
    public static final String TARIFF_NAME = "name";
    public static final String TARIFF_DESCRIPTION = "description";
    public static final String TARIFF_STATE = "state";
    public static final String TARIFF_INTERNET_SPEED = "internet_speed";
    public static final String TARIFF_RATING = "rating";
    public static final String TARIFF_IMG_URL = "image_url";
    public static final String TARIFF_PRICE = "price";

    public static final String SPECIAL_OFFER_TITLE = "title";
    public static final String SPECIAL_OFFER_DESCRIPTION = "description";
    public static final String SPECIAL_OFFER_START_DATE = "start_date";
    public static final String SPECIAL_OFFER_EXPIRATION_DATE = "expiration_date";
    public static final String SPECIAL_OFFER_DISCOUNT = "discount";

    public static final String FEEDBACK_RATING = "rating";
    public static final String FEEDBACK_BODY = "body";
    public static final String FEEDBACK_USER_ID = "user_id";
    public static final String FEEDBACK_TARIFF_ID = "tariff_id";

    private ColumnName() {

    }


}
