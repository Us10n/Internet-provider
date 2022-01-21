package com.epamjwd.provider.model.dao.mapper;

public final class ColumnName {

    public static final String ID = "id";

    public static final String USER_PASSWORD = "users.password";
    public static final String USER_EMAIL = "users.email";
    public static final String USER_FIRST_NAME = "users.first_name";
    public static final String USER_LAST_NAME = "users.last_name";
    public static final String USER_STATUS = "users.status";
    public static final String USER_ROLE = "users.role";
    public static final String USER_TOKEN = "users.token";

    public static final String BANK_ACCOUNT_BALANCE = "bankaccounts.balance";
    public static final String BANK_ACCOUNT_USER_ID = "bankaccounts.user_id";
    public static final String BANK_ACCOUNT_TARIFF_ID = "bankaccounts.tariffs_id";

    public static final String TARIFF_SPECIAL_OFFER_ID = "tariffs.special_offers_id";
    public static final String TARIFF_NAME = "tariffs.name";
    public static final String TARIFF_DESCRIPTION = "tariffs.description";
    public static final String TARIFF_STATUS = "tariffs.status";
    public static final String TARIFF_INTERNET_SPEED = "tariffs.internet_speed";
    public static final String TARIFF_RATING = "tariffs.rating";
    public static final String TARIFF_IMAGE = "tariffs.image_url";
    public static final String TARIFF_PRICE = "tariffs.price";

    public static final String SPECIAL_OFFER_TITLE = "specialoffers.title";
    public static final String SPECIAL_OFFER_DESCRIPTION = "specialoffers.description";
    public static final String SPECIAL_OFFER_START_DATE = "specialoffers.start_date";
    public static final String SPECIAL_OFFER_EXPIRATION_DATE = "specialoffers.expiration_date";
    public static final String SPECIAL_OFFER_DISCOUNT = "specialoffers.discount";
    public static final String SPECIAL_OFFER_IMAGE = "specialoffers.image_url";

    public static final String FEEDBACK_RATING = "feedbacks.rating";
    public static final String FEEDBACK_BODY = "feedbacks.body";
    public static final String FEEDBACK_USER_ID = "feedbacks.users_id";
    public static final String FEEDBACK_TARIFF_ID = "feedbacks.tariffs_id";

    private ColumnName() {

    }


}
