package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.model.dao.mapper.ColumnName;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.entity.SpecialOffer;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.entity.TariffStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TariffMapper implements RowMapper<Tariff> {
    @Override
    public Tariff map(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        if (hasColumn(resultSet, ColumnName.BANK_ACCOUNT_TARIFF_ID)) {
            tariff.setId(resultSet.getLong(ColumnName.BANK_ACCOUNT_TARIFF_ID));
        } else if (hasColumn(resultSet, ColumnName.FEEDBACK_TARIFF_ID)) {
            tariff.setId(resultSet.getLong(ColumnName.FEEDBACK_TARIFF_ID));
        } else {
            tariff.setId(resultSet.getLong(ColumnName.ID));
        }
        tariff.setName(resultSet.getString(ColumnName.TARIFF_NAME));
        tariff.setDescription(resultSet.getString(ColumnName.TARIFF_DESCRIPTION));
        tariff.setPrice(resultSet.getBigDecimal(ColumnName.TARIFF_PRICE));
        tariff.setStatus(TariffStatus.valueOf(resultSet.getString(ColumnName.TARIFF_STATUS)));
        tariff.setInternetSpeed(resultSet.getLong(ColumnName.TARIFF_INTERNET_SPEED));
        tariff.setRating(resultSet.getDouble(ColumnName.TARIFF_RATING));
        tariff.setImage(resultSet.getString(ColumnName.TARIFF_IMAGE));

        SpecialOffer specialOffer = null;
        if (resultSet.getLong(ColumnName.TARIFF_SPECIAL_OFFER_ID) != 0) {
            specialOffer = new SpecialOfferMapper().map(resultSet);
        }
        tariff.setSpecialOffer(specialOffer);
        return tariff;
    }
}
