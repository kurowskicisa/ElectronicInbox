package com.ctk.dao;


import com.ctk.model.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SessionScoped
public class UserRepository implements Serializable {

    private List<User> users = new ArrayList<>();

    public List<User> getList() {
        return users;
    }

    public void setList(User user) {
        this.users.add(user);
    }

    public void clearList() {
        this.users.clear();
    }

    public boolean isAutenticated(String username, String password) {
      return users.stream()
              .filter(u -> u.getName().equals(username)
                && u.getPassword().equals(password))
              .anyMatch(u -> u.getName().equals(username)
                     && u.getPassword().equals(password));
    }

    public void add(String username, String password){
        users.add(new User(username, password));
    }
}
