package com.unitybars.r2d2.service;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.dao.ServiceDao;
import com.unitybars.r2d2.dao.ServiceTypeParameterValueDao;
import com.unitybars.r2d2.entity.Service;
import com.unitybars.r2d2.entity.ServiceStatus;
import com.unitybars.r2d2.entity.ServiceType;
import com.unitybars.r2d2.entity.ServiceTypeParameterValue;
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
    @Mock
    private ServiceTypeParameterValueDao serviceTypeParameterValueDao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        service.setServiceDao(serviceDao);
        service.setServiceTypeParameterValueDao(serviceTypeParameterValueDao);
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
    public void getAllServicesWithParameters() {
        List<Service> services = new ArrayList<>();
        services.add(new Service(1, "Service 1", ServiceType.WEB, ServiceStatus.ACTIVE));
        services.add(new Service(2, "Service 2", ServiceType.SQL, ServiceStatus.ACTIVE));
        services.add(new Service(3, "Service 3", ServiceType.WEB, ServiceStatus.ACTIVE));
        services.add(new Service(4, "Service 4", ServiceType.SQL, ServiceStatus.ACTIVE));
        List<ServiceTypeParameterValue> parameters = new ArrayList<>();
        parameters.add(new ServiceTypeParameterValue(1, 1, "url", "http://test1.com"));
        parameters.add(new ServiceTypeParameterValue(2, 2, "connection_string", "test connection string1"));
        parameters.add(new ServiceTypeParameterValue(3, 3, "url", "http://test2.com"));
        parameters.add(new ServiceTypeParameterValue(4, 4, "connection_string", "test connection string2"));
        when(serviceDao.getAllServices()).thenReturn(services);
        when(serviceTypeParameterValueDao.getAllServiceTypeParameterValues()).thenReturn(parameters);
        List<Service> serviceList = service.getAllServicesWithParameters();
        assertNotNull(serviceList);
        assertEquals(4, serviceList.size());
        assertNotNull(serviceList.get(1));
        assertNotNull(serviceList.get(1).getParameters());
        assertEquals(1, serviceList.get(0).getParameters().size());
        assertEquals("http://test1.com", serviceList.get(0).getParameters().get("url"));
        assertEquals(1, serviceList.get(1).getParameters().size());
        assertEquals("test connection string1", serviceList.get(1).getParameters().get("connection_string"));
        assertEquals(1, serviceList.get(2).getParameters().size());
        assertEquals("http://test2.com", serviceList.get(2).getParameters().get("url"));
        assertEquals(1, serviceList.get(3).getParameters().size());
        assertEquals("test connection string2", serviceList.get(3).getParameters().get("connection_string"));
    }

    @Test
    public void testGetServiceExist() {
        Service serviceItem = new Service(1, "Service 1", ServiceType.WEB, ServiceStatus.ACTIVE);
        List<ServiceTypeParameterValue> serviceTypeParameterValues = new ArrayList<>();
        serviceTypeParameterValues.add(
                new ServiceTypeParameterValue(1, 1, "url", "https://test.com"));
        when(serviceDao.getServiceById(1)).thenReturn(serviceItem);
        when(serviceTypeParameterValueDao.getServiceTypeParameterValuesForService(serviceItem.getId()))
                .thenReturn(serviceTypeParameterValues);
        assertEquals(1, service.getServiceById(1).getId());
        assertNotNull(service.getServiceById(1).getParameters());
        assertEquals(1, service.getServiceById(1).getParameters().size());
        assertEquals("https://test.com", service.getServiceById(1).getParameters().get("url"));
    }


}