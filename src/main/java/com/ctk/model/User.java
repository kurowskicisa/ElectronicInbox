package com.ctk.model;

public class User implements com.ctk.services.User {
    private String name;
    private String password;
    private boolean autenticate;

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
    public boolean isAutenticate() {
        return autenticate;
    }

    @Override
    public void setAutenticate(boolean autenticate) {
        this.autenticate = autenticate;
    }

}
