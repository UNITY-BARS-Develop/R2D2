package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.ServiceDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
public class ServiceDaoTest extends AbstractDaoTest {

    @Autowired
    private ServiceDao serviceDao;

    @Test
    public void testInit() {
        assertNotNull(serviceDao);
    }

    @Test
    public void testGetAllServices() {
        // TODO
    }

    @Test
    public void testGetServiceById() {
        // TODO
    }
}
