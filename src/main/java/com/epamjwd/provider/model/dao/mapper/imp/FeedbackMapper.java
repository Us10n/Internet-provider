package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.model.dao.mapper.ColumnName;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.entity.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements RowMapper<Feedback> {
    @Override
    public Feedback map(ResultSet resultSet) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(resultSet.getLong(ColumnName.ID));
        feedback.setFeedbackBody(resultSet.getString(ColumnName.FEEDBACK_BODY));
        feedback.setRating(resultSet.getInt(ColumnName.FEEDBACK_RATING));
        feedback.setTariffId(resultSet.getLong(ColumnName.FEEDBACK_TARIFF_ID));
        feedback.setAuthorId(resultSet.getLong(ColumnName.FEEDBACK_USER_ID));
        feedback.setAuthorName(resultSet.getString(ColumnName.USER_FIRST_NAME));
        return feedback;
    }
}
