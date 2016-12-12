package com.unitybars.r2d2.service;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceStatus;
import com.unitybars.r2d2.entity.ServiceType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
@Transactional
public class ServiceServiceTest extends AbstractTest {

    @Autowired
    private ServiceService service;

    @Mock
    private ServiceDao serviceDao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        service.setServiceDao(serviceDao);
    }

    @Test
    public void testInit() {
        assertNotNull(serviceDao);
        assertNotNull(service);
    }

    @Test
    public void testGetAllServices() {
        List<Service> services = new ArrayList<>();
        services.add(new Service());
        services.add(new Service());
        services.add(new Service());
        when(serviceDao.getAllServices()).thenReturn(services);
        assertEquals(services.size(), service.getAllServices().size());
    }

    @Test
    public void testGetServiceExist() {
        Service serviceItem = new Service(1, "Service 1", ServiceType.WEB, ServiceStatus.ACTIVE);
        when(serviceDao.getServiceById(1)).thenReturn(serviceItem);
        assertEquals(1, service.getServiceById(1).getId());
    }

    @Test(expected = NullPointerException.class)
    public void testGetServiceNotFound() {
        when(serviceDao.getServiceById(0)).thenReturn(null);
        assertEquals(0, service.getServiceById(0).getId());
    }

}
