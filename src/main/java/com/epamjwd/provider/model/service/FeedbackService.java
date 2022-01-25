package com.epamjwd.provider.model.service;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> findFeedbacksByTariffId(Long tariffId) throws ServiceException;

    boolean createNewFeedback(Long authorId, String tariffId, String rating, String feedbackBody) throws ServiceException;
}
