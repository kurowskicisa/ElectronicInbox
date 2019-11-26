package com.ctk.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;

import static java.lang.String.*;

public class GrayScaleDao extends com.ctk.model.GrayScale implements Serializable {

    public void loadGrayScaleFile() {
        String line;
        BufferedReader reader;

        setGrayScale("000");

        Settings settings = new Settings();

        if (new File(valueOf(settings.getPathGrayScaleInfo())).isFile()) {

            try {

                reader = Files.newBufferedReader(settings.getPathGrayScaleInfo(), StandardCharsets.UTF_8);

                line = Optional.ofNullable(reader.readLine()).orElse("000");

                if (!line.isEmpty()) {
                    if (line.matches("[0-9]{0,3}")) {
                        int testValue = Integer.parseInt(line);
                        if (testValue >= 0 && testValue <= 100) {
                            setGrayScale(line);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
