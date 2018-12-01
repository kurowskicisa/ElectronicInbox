package com.ctk.model;

public class User {
    private String name;
    private String password;
    private boolean autenticate;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAutenticate() {
        return autenticate;
    }

    public void setAutenticate(boolean autenticate) {
        this.autenticate = autenticate;
    }


}
