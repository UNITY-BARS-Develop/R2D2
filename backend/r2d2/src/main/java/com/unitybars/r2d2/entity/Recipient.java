package com.unitybars.r2d2.entity;

/**
 * Created by oleg.nestyuk
 * Date: 09-Mar-17.
 */
public class Recipient {
    private int id;
    private String name;
    private String email;

    public Recipient() {
    }

    public Recipient(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}