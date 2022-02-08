package com.epamjwd.provider.model.service.validator;

/**
 * The interface Special offer validator.
 */
public interface SpecialOfferValidator {
    /**
     * Validates title.
     *
     * @param title the title
     * @return the boolean
     */
    boolean isTitleValid(String title);

    /**
     * Validates description.
     *
     * @param description the description
     * @return the boolean
     */
    boolean isDescriptionValid(String description);

    /**
     * Validate date.
     *
     * @param date the date
     * @return the boolean
     */
    boolean isDateValid(String date);

    /**
     * Validate discount.
     *
     * @param discount the discount
     * @return the boolean
     */
    boolean isDiscountValid(String discount);

    /**
     * Validate name.
     *
     * @param image the image
     * @return the boolean
     */
    boolean isImageNameValid(String image);
}
