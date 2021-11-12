package com.ctk.services;

public interface User {

    String getName();

    String getPassword();

    boolean isAuthenticate();

    void setAuthenticate(boolean authenticate);

}
