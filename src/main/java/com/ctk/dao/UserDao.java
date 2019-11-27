package com.ctk.dao;

import com.ctk.model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SessionScoped
public class UserDao extends com.ctk.model.User implements Serializable {

    @Inject
    private Settings settings;

    private List<User> users = new ArrayList<>();

    public List<User> getList() {
        return users;
    }

    public boolean isAutenticated(String username, String password) {
        return users.stream()
                .filter(u -> u.getName().equals(username)
                        && u.getPassword().equals(password))
                .anyMatch(u -> u.getName().equals(username)
                        && u.getPassword().equals(password));
    }

    public void add(String username, String password) {
        users.add(new User(username, password));
    }

    public void empty() {
        users.clear();
    }

    public void loadUserFile() {
        String line = null;
        BufferedReader reader = null;

        if (new File(String.valueOf(settings.getPathAdmin())).isFile()) {

            try {

                reader = Files.newBufferedReader(settings.getPathAdmin(), StandardCharsets.UTF_8);
                line = reader.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (reader != null) {

                if (line != null && !line.isEmpty()) {

                    try {
                        readingAllUsers(line, reader);
                        reader.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void readingAllUsers(
            String line,
            BufferedReader reader)
            throws IOException {

        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(";"));

                add(tempList.get(0), tempList.get(1));
            }
            line = reader.readLine();
        }

    }
}
