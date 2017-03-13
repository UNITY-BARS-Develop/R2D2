package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceStatus;
import com.unitybars.r2d2.entity.ServiceType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
public class SqliteServiceDaoTest extends AbstractDaoTest {

    @Autowired
    private ServiceDao serviceDao;

    @Test
    public void testInit() {
        assertNotNull(serviceDao);
    }

    @Test
    public void testGetAllServices() {
        assertEquals(6, serviceDao.getAllServices().size());
    }

    @Test
    public void testGetServiceById() {
        Service service = serviceDao.getServiceById("1");
        assertEquals("1", service.getId());
        assertEquals("Service 1", service.getName());
        assertEquals(ServiceStatus.ACTIVE, service.getServiceStatus());
        assertEquals(ServiceType.WEB, service.getServiceType());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getNonexistentService() {
        serviceDao.getServiceById("7");
    }
}
