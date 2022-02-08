package com.epamjwd.provider.model.service.validator;

/**
 * The interface Feed back validator.
 */
public interface FeedBackValidator {
    /**
     * Validate text body.
     *
     * @param textBody the text body
     * @return the boolean
     */
    boolean isTextBodyValid(String textBody);

    /**
     * Validates rating.
     *
     * @param rating the rating
     * @return the boolean
     */
    boolean isRatingValid(String rating);
}
