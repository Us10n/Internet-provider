package com.epamjwd.provider.model.dao;

import com.epamjwd.provider.model.entity.Identifiable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class AbstractQueryExecutor<T extends Identifiable> {

    private static final Logger logger = LogManager.getLogger();

    protected List<T> executeQuery(String query, Object... parameters){

    }


}
