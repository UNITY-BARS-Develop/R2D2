package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.entity.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
@Repository
@Qualifier("databaseData")
public class SQLiteServiceDao implements ServiceDao {
    @Override
    public List<Service> getAllServices() {
        return null;    // TODO implement
    }

    @Override
    public Service getServiceById(int id) {
        return null;    // TODO implement
    }
}
