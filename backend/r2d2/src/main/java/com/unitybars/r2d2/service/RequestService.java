package com.unitybars.r2d2.service;

import com.unitybars.r2d2.entity.HeaderItem;
import com.unitybars.r2d2.entity.RequestMethod;
import com.unitybars.r2d2.exception.RequestExecuteError;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestService {
    private Logger logger = LoggerFactory.getLogger(RequestService.class);

    @Value("${request.connect_timeout}")
    protected int connectedTimeout;

    @Value("${request.read_timeout}")
    protected int readTimeout;

    public void setConnectedTimeout(int connectedTimeout) {
        this.connectedTimeout = connectedTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Response executeResponse(String url, RequestMethod requestMethod, List<HeaderItem> headerItems)
            throws RequestExecuteError {
        OkHttpClient client = generateUnsafeClient();
        Request request = generateRequest(url, requestMethod, headerItems);
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            logger.error("Error when try execute request.", e);
            throw new RequestExecuteError(url);
        }
    }

    protected OkHttpClient generateClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(connectedTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();
    }

    protected OkHttpClient generateUnsafeClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder
                    .connectTimeout(connectedTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Request generateRequest(@NotNull String url, @NotNull RequestMethod requestMethod, List<HeaderItem> headerItems) {
        Request request;
        Request.Builder builder = new Request.Builder()
                .url(url)
                .headers(generateHeaders(headerItems));
        switch (requestMethod) {
            case POST:
                builder.post(RequestBody.create(null, new byte[0]));
                break;
            case PUT:
                builder.put(RequestBody.create(null, new byte[0]));
                break;
            case DELETE:
                builder.delete(RequestBody.create(null, new byte[0]));
                break;
            default:
                builder.get();
                break;
        }
        return builder.build();
    }

    protected Headers generateHeaders(List<HeaderItem> headerItems) {
        if (headerItems != null) {
            Headers.Builder builder = new Headers.Builder();
            for (HeaderItem header : headerItems) {
                if (header.getKey() != null && header.getValue() != null) {
                    builder.add(header.getKey(), header.getValue());
                }
            }
            return builder.build();
        }
        return new Headers.Builder().build();
    }
}