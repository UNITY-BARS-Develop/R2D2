package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class MailSettings {
    private String host;
    private String username;
    private String password;
    private String port;
    private boolean startTlsEnable;

    public MailSettings(String host, String username, String password, String port, boolean startTlsEnable) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
        this.startTlsEnable = startTlsEnable;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isStartTlsEnable() {
        return startTlsEnable;
    }

    public void setStartTlsEnable(boolean startTlsEnable) {
        this.startTlsEnable = startTlsEnable;
    }
}