package com.epamjwd.provider.model.dao.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.AbstractQueryExecutor;
import com.epamjwd.provider.model.dao.FeedbackDao;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Feedback;

import java.util.List;
import java.util.Optional;

public class FeedBackDao extends AbstractQueryExecutor<Feedback> implements FeedbackDao {

    private static final String FIND_ALL_FEEDBACK_QUERY = """
            SELECT feedbacks.id, feedbacks.users_id, feedbacks.tariffs_id, feedbacks.rating, feedbacks.body FROM internetprovider.feedbacks""";
    private static final String FIND_FEEDBACK_BY_FEEDBACK_ID_QUERY = """
            SELECT feedbacks.id, feedbacks.users_id, feedbacks.tariffs_id, feedbacks.rating, feedbacks.body FROM internetprovider.feedbacks""";
    private static final String FIND_FEEDBACK_BY_USER_ID_QUERY = """
            SELECT feedbacks.id, feedbacks.users_id, feedbacks.tariffs_id, feedbacks.rating, feedbacks.body FROM internetprovider.feedbacks
            WHERE feedbacks.users_id=?""";
    private static final String FIND_FEEDBACK_BY_TARIFF_ID_QUERY = """
            SELECT feedbacks.id, feedbacks.users_id, feedbacks.tariffs_id, feedbacks.rating, feedbacks.body FROM internetprovider.feedbacks
            WHERE feedbacks.tariffs_id=?""";
    private static final String INSERT_FEEDBACK_QUERY = """
            INSERT INTO internetprovider.feedbacks (users_id, tariffs_id, rating, body)
            VALUES (?, ?, ?, ?)""";

    public FeedBackDao() {
        super(RowMapperFactory.getInstance().getFeedbackRowMapper());
    }

    @Override
    public List<Feedback> findAll() throws DaoException {
        return executeQuery(FIND_ALL_FEEDBACK_QUERY);
    }

    @Override
    public Optional<Feedback> findById(long id) throws DaoException {
        List<Feedback> tariffList = executeQuery(FIND_FEEDBACK_BY_FEEDBACK_ID_QUERY, id);
        return tariffList.size() != 1 ? Optional.empty() : Optional.of(tariffList.get(0));
    }

    @Override
    public List<Feedback> findByUserId(long userId) throws DaoException {
        return executeQuery(FIND_FEEDBACK_BY_USER_ID_QUERY, userId);
    }

    @Override
    public List<Feedback> findByTariffId(long tariffId) throws DaoException {
        return executeQuery(FIND_FEEDBACK_BY_TARIFF_ID_QUERY, tariffId);
    }

    @Override
    public long create(Feedback feedback) throws DaoException {
        return executeInsertQuery(INSERT_FEEDBACK_QUERY,
                feedback.getAuthorId(), feedback.getTariffId(),
                feedback.getRating(), feedback.getFeedbackBody());
    }


}
