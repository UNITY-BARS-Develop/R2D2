package com.unitybars.r2d2.dao.fake;

import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceStatus;
import com.unitybars.r2d2.entity.ServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg.nestyuk
 * Date: 08-Dec-16.
 */
@Repository
@Qualifier("fakeData")
public class FakeServiceDao implements ServiceDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static List<Service> services;

    static {
        services = new ArrayList<Service>() {
            {
                add(new Service(1, "Server 1", ServiceType.WEB, ServiceStatus.ACTIVE));
                add(new Service(2, "Server 2", ServiceType.WEB, ServiceStatus.DELETED));
                add(new Service(3, "Server 3", ServiceType.WEB, ServiceStatus.PAUSED));
                add(new Service(4, "Server 4", ServiceType.SQL, ServiceStatus.ACTIVE));
                add(new Service(5, "Server 5", ServiceType.SQL, ServiceStatus.DELETED));
                add(new Service(6, "Server 6", ServiceType.SQL, ServiceStatus.PAUSED));
            }
        };
    }

    @Override
    public List<Service> getAllServices() {
        logger.info("Fake data. Get all services called");
        return services;
    }

    @Override
    public Service getServiceById(int id) {
        logger.info(String.format("Fake data. Get service with id %d called", id));
        return services.get(id - 1);
    }
}