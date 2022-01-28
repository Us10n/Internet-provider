package com.epamjwd.provider.model.service.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.dao.DaoHolder;
import com.epamjwd.provider.model.dao.FeedbackDao;
import com.epamjwd.provider.model.dao.TariffDao;
import com.epamjwd.provider.model.entity.Feedback;
import com.epamjwd.provider.model.service.FeedbackService;
import com.epamjwd.provider.model.service.validator.FeedBackValidator;
import com.epamjwd.provider.model.service.validator.impl.FeedBackValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Feedback> findFeedbacksByTariffId(Long tariffId) throws ServiceException {
        if (tariffId == null) {
            return new ArrayList<>();
        }

        FeedbackDao feedbackDao = DaoHolder.getInstance().getFeedBackDao();
        try {
            List<Feedback> feedbackList ;
            feedbackList = feedbackDao.findByTariffId(tariffId);
            return feedbackList;
        } catch (DaoException e) {
            logger.error("Feedbacks find by tariff id error", e);
            throw new ServiceException("Feedbacks find by tariff id error", e);
        }
    }

    @Override
    public boolean createNewFeedback(Long authorId, String tariffId, String rating, String feedbackBody) throws ServiceException {
        if (tariffId == null || authorId == null || !isFeedbackFormValid(rating, feedbackBody)) {
            return false;
        }

        FeedbackDao feedbackDao = DaoHolder.getInstance().getFeedBackDao();
        TariffDao tariffDao = DaoHolder.getInstance().getTariffDao();
        try {
            int ratingInt = Integer.parseInt(rating);
            long tariffIdLong = Long.parseLong(tariffId);
            if (tariffDao.findById(tariffIdLong).isEmpty()) {
                return false;
            }
            Feedback feedback = new Feedback(ratingInt, feedbackBody, tariffIdLong, authorId);
            feedbackDao.create(feedback);
            tariffDao.updateTariffRating();
        } catch (DaoException e) {
            logger.error("Feedbacks create error", e);
            throw new ServiceException("Feedbacks create error", e);
        }
        return true;
    }

    private boolean isFeedbackFormValid(String rating, String feedbackBody) {
        FeedBackValidator feedBackValidator = FeedBackValidatorImpl.getInstance();
        return feedBackValidator.isTextBodyValid(feedbackBody) &&
                feedBackValidator.isRatingValid(rating);
    }
}
