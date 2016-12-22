package com.unitybars.r2d2.service;

import com.unitybars.r2d2.AbstractTest;
import com.unitybars.r2d2.entity.HeaderItem;
import com.unitybars.r2d2.entity.RequestMethod;
import com.unitybars.r2d2.exception.RequestExecuteError;
import okhttp3.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.HostnameVerifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oleg.nestyuk
 * Date: 21-Dec-16.
 */
public class RequestServiceTest extends AbstractTest {

    @Autowired
    private RequestService requestService;
    private int connectedTimeoutInSeconds = 10;
    private int readTimeoutInSeconds = 20;

    @Before
    public void init() {
        requestService.setConnectedTimeout(connectedTimeoutInSeconds);
        requestService.setReadTimeout(readTimeoutInSeconds);
    }

    @Test
    public void executeResponse() throws Exception {
        Response response = requestService.executeResponse("https://jsonplaceholder.typicode.com/posts/1",
                RequestMethod.GET, null);
        assertNotNull(response);
        assertEquals(200, response.code());
    }

    @Test(expected = RequestExecuteError.class)
    public void executeNotExistResponse() throws Exception {
        Response response = requestService.executeResponse("http://notexisturl.unity-bars.com",
                RequestMethod.GET, null);
        assertNotNull(response);
        assertEquals(404, response.code());
    }

    @Test
    public void generateClient() throws Exception {
        OkHttpClient client = requestService.generateClient();
        assertNotNull(client);
        assertEquals(connectedTimeoutInSeconds * 1000, client.connectTimeoutMillis());
        assertEquals(readTimeoutInSeconds * 1000, client.readTimeoutMillis());
    }

    @Test
    public void generateUnsafeClient() throws Exception {
        OkHttpClient client = requestService.generateUnsafeClient();
        assertNotNull(client);
        assertEquals(connectedTimeoutInSeconds * 1000, client.connectTimeoutMillis());
        assertEquals(readTimeoutInSeconds * 1000, client.readTimeoutMillis());
        HostnameVerifier hostnameVerifier = client.hostnameVerifier();
        assertNotNull(hostnameVerifier);
        assertEquals(true, hostnameVerifier.verify("any_string", null));
    }

    @Test
    public void generateGetRequest() throws Exception {
        Request request = requestService.generateRequest("https://test.com", RequestMethod.GET, getHeadersList());
        assertNotNull(request);
        assertEquals("GET", request.method());
        HttpUrl httpUrl = request.url();
        assertNotNull(httpUrl);
        assertNull(httpUrl.encodedQuery());
        assertEquals("/", httpUrl.encodedPath());
        assertEquals("test.com", httpUrl.url().getHost());
        assertEquals(true, httpUrl.isHttps());
        assertEquals(6, request.headers().size());
    }

    @Test
    public void generatePostRequest() throws Exception {
        Request request = requestService.generateRequest("http://test.com", RequestMethod.POST, getHeadersList());
        assertNotNull(request);
        assertEquals("POST", request.method());
        HttpUrl httpUrl = request.url();
        assertNotNull(httpUrl);
        assertNull(httpUrl.encodedQuery());
        assertEquals("/", httpUrl.encodedPath());
        assertEquals("test.com", httpUrl.url().getHost());
        assertEquals(false, httpUrl.isHttps());
        assertEquals(6, request.headers().size());
    }

    @Test
    public void generatePutRequest() throws Exception {
        Request request = requestService.generateRequest("http://test.com/foo", RequestMethod.PUT, new ArrayList<>());
        assertNotNull(request);
        assertEquals("PUT", request.method());
        HttpUrl httpUrl = request.url();
        assertNotNull(httpUrl);
        assertNull(httpUrl.encodedQuery());
        assertEquals("/foo", httpUrl.encodedPath());
        assertEquals("test.com", httpUrl.url().getHost());
        assertEquals(false, httpUrl.isHttps());
        assertEquals(0, request.headers().size());
    }

    @Test
    public void generateDeleteRequest() throws Exception {
        Request request = requestService.generateRequest("https://test.com/foo", RequestMethod.DELETE, null);
        assertNotNull(request);
        assertEquals("DELETE", request.method());
        HttpUrl httpUrl = request.url();
        assertNotNull(httpUrl);
        assertNull(httpUrl.encodedQuery());
        assertEquals("/foo", httpUrl.encodedPath());
        assertEquals("test.com", httpUrl.url().getHost());
        assertEquals(true, httpUrl.isHttps());
        assertEquals(0, request.headers().size());
    }

    @Test
    public void generateHeaders() throws Exception {
        List<HeaderItem> headerItemList = getHeadersList();
        Headers headers = requestService.generateHeaders(headerItemList);
        assertNotNull(headers);
        assertEquals(6, headers.size());
        assertEquals("header_value_1", headers.get("header_key_1"));
        assertEquals("header_value_5", headers.get("header_key_4"));
        assertEquals("header_value_4", headers.get("header_key_3"));
        List<String> multiHeaders = headers.values("header_key_3");
        assertEquals(2, multiHeaders.size());
        assertEquals("header_value_3", multiHeaders.get(0));
        assertEquals("header_value_4", multiHeaders.get(1));
    }

    @Test
    public void generateNonExistHeaders() throws Exception {
        Headers headers = requestService.generateHeaders(new ArrayList<>());
        assertNotNull(headers);
        assertEquals(0, headers.size());
    }

    @Test
    public void generateNullHeaders() throws Exception {
        Headers headers = requestService.generateHeaders(null);
        assertEquals(0, headers.size());
    }

    public List<HeaderItem> getHeadersList() {
        List<HeaderItem> headerItems = new ArrayList<>();
        headerItems.add(new HeaderItem("header_key_1", "header_value_1"));
        headerItems.add(new HeaderItem("header_key_2", "header_value_2"));
        headerItems.add(new HeaderItem("header_key_3", "header_value_3"));
        headerItems.add(new HeaderItem("header_key_3", "header_value_4"));
        headerItems.add(new HeaderItem("header_key_4", "header_value_5"));
        headerItems.add(new HeaderItem("header_key_5", "header_value_6"));
        return headerItems;
    }
}