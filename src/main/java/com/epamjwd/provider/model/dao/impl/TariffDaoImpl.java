package com.epamjwd.provider.model.dao.impl;

import com.epamjwd.provider.exception.DaoException;
import com.epamjwd.provider.model.dao.AbstractQueryExecutor;
import com.epamjwd.provider.model.dao.TariffDao;
import com.epamjwd.provider.model.dao.mapper.RowMapperFactory;
import com.epamjwd.provider.model.entity.Tariff;
import com.epamjwd.provider.model.entity.TariffStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TariffDaoImpl extends AbstractQueryExecutor<Tariff> implements TariffDao {

    private static final String FIND_ALL_TARIFF_QUERY = """
            SELECT  tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs
            LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id;""";
    private static final String FIND_ALL_TARIFF_QUERY_SORT_BY_NAME = """
            SELECT  tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs
            LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id ORDER BY tariffs.name ASC;""";
    private static final String FIND_ALL_TARIFF_QUERY_SORT_BY_PRICE = """
            SELECT  tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs
            LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id ORDER BY tariffs.price ASC;""";
    private static final String FIND_ALL_TARIFF_QUERY_SORT_BY_INTERNET_SPEED = """
            SELECT  tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs
            LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id ORDER BY tariffs.internet_speed ASC;""";
    private static final String FIND_ALL_TARIFF_QUERY_SORT_BY_RATING = """
            SELECT  tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs
            LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id ORDER BY tariffs.rating ASC;""";
    private static final String FIND_TARIFF_BY_ID_QUERY = """
            SELECT tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id
            WHERE tariffs.id=?""";
    private static final String FIND_TARIFFS_BY_NAME_QUERY = """
            SELECT tariffs.id, tariffs.special_offers_id, tariffs.name,tariffs.description,
                    tariffs.status, tariffs.internet_speed,tariffs.rating,tariffs.image_url,tariffs.price,
                    specialoffers.title,specialoffers.description, specialoffers.start_date,
                    specialoffers.expiration_date,specialoffers.discount,specialoffers.image_url
            FROM internetprovider.tariffs LEFT JOIN internetprovider.specialoffers
            ON tariffs.special_offers_id=specialoffers.id
            WHERE tariffs.name=?""";
    private static final String INSERT_TARIFF_QUERY = """
            INSERT INTO internetprovider.tariffs (name, description,internet_speed, image_url, price)
            VALUES (?, ?, ?, ?, ?)""";
    private static final String UPDATE_SPECIAL_OFFER_ID_QUERY = """
            UPDATE internetprovider.tariffs SET special_offers_id = ? WHERE id = ?""";
    private static final String UPDATE_STATUS_QUERY = """
            UPDATE internetprovider.tariffs SET status = ? WHERE id = ?""";
    private static final String UPDATE_PRICE_QUERY = """
            UPDATE internetprovider.tariffs SET price = ? WHERE id = ?""";

    public TariffDaoImpl() {
        super(RowMapperFactory.getInstance().getTariffRowMapper());
    }

    @Override
    public List<Tariff> findAll() throws DaoException {
        return executeQuery(FIND_ALL_TARIFF_QUERY);
    }

    @Override
    public List<Tariff> findAllSortByName() throws DaoException {
        return executeQuery(FIND_ALL_TARIFF_QUERY_SORT_BY_NAME);
    }

    @Override
    public List<Tariff> findAllSortByInternetSpeed() throws DaoException {
        return executeQuery(FIND_ALL_TARIFF_QUERY_SORT_BY_INTERNET_SPEED);
    }

    @Override
    public List<Tariff> findAllSortByPrice() throws DaoException {
        return executeQuery(FIND_ALL_TARIFF_QUERY_SORT_BY_PRICE);
    }

    @Override
    public List<Tariff> findAllSortByRating() throws DaoException {
        return executeQuery(FIND_ALL_TARIFF_QUERY_SORT_BY_RATING);
    }

    @Override
    public Optional<Tariff> findById(long id) throws DaoException {
        List<Tariff> tariffList = executeQuery(FIND_TARIFF_BY_ID_QUERY, id);
        return tariffList.size() != 1 ? Optional.empty() : Optional.of(tariffList.get(0));
    }

    @Override
    public Optional<Tariff> findByName(String tariffName) throws DaoException {
        List<Tariff> tariffList = executeQuery(FIND_TARIFFS_BY_NAME_QUERY, tariffName);
        return tariffList.size() != 1 ? Optional.empty() : Optional.of(tariffList.get(0));
    }

    @Override
    public long create(Tariff tariff) throws DaoException {
        return executeInsertQuery(INSERT_TARIFF_QUERY,
                tariff.getName(), tariff.getDescription(),
                tariff.getInternetSpeed(), tariff.getImage(), tariff.getPrice());
    }

    @Override
    public void updateSpecialOfferId(long tariffId, long specialOfferId) throws DaoException {
        executeUpdateQuery(UPDATE_SPECIAL_OFFER_ID_QUERY, specialOfferId, tariffId);
    }

    @Override
    public void updateStatus(long tariffId, TariffStatus tariffStatus) throws DaoException {
        executeUpdateQuery(UPDATE_STATUS_QUERY, tariffStatus.toString(), tariffId);
    }

    @Override
    public void updatePrice(long tariffId, BigDecimal newPrice) throws DaoException {
        executeUpdateQuery(UPDATE_PRICE_QUERY, newPrice, tariffId);
    }

    @Override
    public void deleteSpecialOfferId(long tariffId) throws DaoException {
        executeUpdateQuery(UPDATE_SPECIAL_OFFER_ID_QUERY, tariffId);
    }
}
