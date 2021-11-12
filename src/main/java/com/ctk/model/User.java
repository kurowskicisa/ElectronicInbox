package com.ctk.model;

public class User implements com.ctk.services.User {
    private String name;
    private String password;
    private boolean authenticate;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAuthenticate() {
        return authenticate;
    }

    @Override
    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

}
