package com.ctk.dao;

import com.ctk.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SessionScoped
public class UserRepository implements Serializable {

    private List<User> users;


    public Optional<User> findBy(String username, String password) {
        return users.stream().filter(u -> u.getName().equals(username)
                && u.getPassword().equals(password)).findAny();
    }

    public void add(String username, String password){
        users.add(new User(username, password));
    }
}
