package com.gamecloud.model.developer;

public class Developer {

    private String id;
    private String password;
    private String email;

    public Developer(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}