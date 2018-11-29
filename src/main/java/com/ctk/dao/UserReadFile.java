package com.ctk.dao;

import com.ctk.model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@SessionScoped
public class UserReadFile implements Serializable {

    @Inject
    private Settings settings; // = new Settings();

    @Inject
    private UserRepository userRepository;

    public void loadUserFile() {
        String line = null;
        BufferedReader reader = null;

        try {

            reader = Files.newBufferedReader(settings.getPathAdmin(), StandardCharsets.UTF_8);
            line = reader.readLine();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (line != null && !line.isEmpty()) {

            try {
                readingAllUsers(line, reader);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readingAllUsers(
            String line,
            BufferedReader reader)
            throws IOException {


        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split("|"));
                userRepository.add(tempList.get(0).trim(), tempList.get(1).trim());
            }
            line = reader.readLine();
        }

    }

}
