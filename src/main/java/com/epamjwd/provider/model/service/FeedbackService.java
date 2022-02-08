package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Feedback;

import java.util.List;

/**
 * The interface Feedback service.
 */
public interface FeedbackService {
    /**
     * Find feedbacks by tariff id list.
     *
     * @param tariffId the tariff id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Feedback> findFeedbacksByTariffId(Long tariffId) throws ServiceException;

    /**
     * Create new feedback boolean.
     *
     * @param authorId     the author id
     * @param tariffId     the tariff id
     * @param rating       the rating
     * @param feedbackBody the feedback body
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createNewFeedback(Long authorId, String tariffId, String rating, String feedbackBody) throws ServiceException;
}
