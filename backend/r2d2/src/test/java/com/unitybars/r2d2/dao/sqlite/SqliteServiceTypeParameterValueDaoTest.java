package com.unitybars.r2d2.dao.sqlite;

import com.unitybars.r2d2.dao.AbstractDaoTest;
import com.unitybars.r2d2.dao.ServiceTypeParameterValueDao;
import com.unitybars.r2d2.entity.ServiceTypeParameterValue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by oleg.nestyuk
 * Date: 14-Dec-16.
 */
public class SqliteServiceTypeParameterValueDaoTest extends AbstractDaoTest {

    @Autowired
    private ServiceTypeParameterValueDao serviceTypeParameterValueDao;

    @Test
    public void testInit() {
        assertNotNull(serviceTypeParameterValueDao);
    }

    @Test
    public void ServiceTypeParameterValues(){
        List<ServiceTypeParameterValue> serviceTypeParameterValues =
                serviceTypeParameterValueDao.getAllServiceTypeParameterValues();
        assertNotNull(serviceTypeParameterValues);
        assertEquals(6, serviceTypeParameterValues.size());
    }

    @Test
    public void getServiceTypeParameterValuesForService() {
        List<ServiceTypeParameterValue> serviceTypeParameterValues =
                serviceTypeParameterValueDao.getServiceTypeParameterValuesForService(1);
        assertNotNull(serviceTypeParameterValues);
        assertEquals(1, serviceTypeParameterValues.size());
        assertNotNull(serviceTypeParameterValues.get(0));
        assertEquals("https://corplightdev.unity-bars.com:4443", serviceTypeParameterValues.get(0).getValue());
        assertEquals(1, serviceTypeParameterValues.get(0).getServiceId());
        assertEquals("url", serviceTypeParameterValues.get(0).getServiceTypeParameter().toLowerCase());
    }

    @Test
    public void getNonexistServiceTypeParameterValuesForService() {
        List<ServiceTypeParameterValue> serviceTypeParameterValues =
                serviceTypeParameterValueDao.getServiceTypeParameterValuesForService(0);
        assertNotNull(serviceTypeParameterValues);
        assertEquals(0, serviceTypeParameterValues.size());
    }

}