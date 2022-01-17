package com.epamjwd.provider.model.dao.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.AbstractQueryExecutor;
import com.epamjwd.provider.model.dao.SpecialOfferDao;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.SpecialOffer;

import java.util.List;
import java.util.Optional;

public class SpecialOfferDaoImpl extends AbstractQueryExecutor<SpecialOffer> implements SpecialOfferDao {

    private static final String FIND_ALL_SPECIAL_OFFERS_QUERY = """
            SELECT specialoffers.id, specialoffers.title, specialoffers.description, specialoffers.start_date,
            specialoffers.expiration_date, specialoffers.discount, specialoffers.image_url 
            FROM internetprovider.specialoffers""";
    private static final String FIND_SPECIAL_OFFERS_BY_ID_QUERY = """
            SELECT specialoffers.id, specialoffers.title, specialoffers.description, specialoffers.start_date,
            specialoffers.expiration_date, specialoffers.discount, specialoffers.image_url
            FROM internetprovider.specialoffers
            WHERE id=?""";
    private static final String INSERT_SPECIAL_OFFER_QUERY = """
            INSERT INTO internetprovider.specialoffers (title, description, start_date, expiration_date, discount, image_url)
            VALUES (?, ?, ?, ?, ?, ?)""";

    public SpecialOfferDaoImpl() {
        super(RowMapperFactory.getInstance().getSpecialOfferRowMapper());
    }

    @Override
    public List<SpecialOffer> findAll() throws DaoException {
        return executeQuery(FIND_ALL_SPECIAL_OFFERS_QUERY);
    }

    @Override
    public Optional<SpecialOffer> findById(long id) throws DaoException {
        List<SpecialOffer> tariffList = executeQuery(FIND_SPECIAL_OFFERS_BY_ID_QUERY, id);
        return tariffList.size() != 1 ? Optional.empty() : Optional.of(tariffList.get(0));
    }

    @Override
    public long create(SpecialOffer specialOffer) throws DaoException {
        return executeInsertQuery(INSERT_SPECIAL_OFFER_QUERY,
                specialOffer.getTitle(), specialOffer.getDescription(),
                specialOffer.getStartDate(), specialOffer.getExpirationDate(),
                specialOffer.getDiscount(), specialOffer.getImage());
    }


}