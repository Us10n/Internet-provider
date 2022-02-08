package com.epamjwd.provider.model.service.validator;

/**
 * The interface Tariff validator.
 */
public interface TariffValidator {
    /**
     * Validate name.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isNameValid(String name);

    /**
     * Validate description.
     *
     * @param description the description
     * @return the boolean
     */
    boolean isDescriptionValid(String description);

    /**
     * Validate internet speed.
     *
     * @param speed the speed
     * @return the boolean
     */
    boolean isInternetSpeedValid(String speed);

    /**
     * Is image name valid boolean.
     *
     * @param image the image
     * @return the boolean
     */
    boolean isImageNameValid(String image);

    /**
     * Is price valid boolean.
     *
     * @param price the price
     * @return the boolean
     */
    boolean isPriceValid(String price);

    /**
     * Is tariff status valid boolean.
     *
     * @param status the status
     * @return the boolean
     */
    boolean isTariffStatusValid(String status);
}
