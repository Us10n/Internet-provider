package com.epamjwd.provider.model.dao.mapper.imp;

import com.epamjwd.provider.model.dao.mapper.ColumnName;
import com.epamjwd.provider.model.dao.mapper.RowMapper;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialOfferMapper implements RowMapper<SpecialOffer> {
    @Override
    public SpecialOffer map(ResultSet resultSet) throws SQLException {
        SpecialOffer specialOffer = new SpecialOffer();
        if(hasColumn(resultSet, ColumnName.TARIFF_SPECIAL_OFFER_ID)){
            specialOffer.setId(resultSet.getLong(ColumnName.TARIFF_SPECIAL_OFFER_ID));
        }else{
            specialOffer.setId(resultSet.getLong(ColumnName.ID));
        }
        specialOffer.setTitle(resultSet.getString(ColumnName.SPECIAL_OFFER_TITLE));
        specialOffer.setDescription(resultSet.getString(ColumnName.SPECIAL_OFFER_DESCRIPTION));
        specialOffer.setDiscount(resultSet.getInt(ColumnName.SPECIAL_OFFER_DISCOUNT));
        specialOffer.setStartDate(resultSet.getDate(ColumnName.SPECIAL_OFFER_START_DATE));
        specialOffer.setExpirationDate(resultSet.getDate(ColumnName.SPECIAL_OFFER_EXPIRATION_DATE));
        specialOffer.setImage(resultSet.getString(ColumnName.SPECIAL_OFFER_IMAGE));

        return specialOffer;
    }
}
