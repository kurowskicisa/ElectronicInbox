package com.ctk.dao;

import com.ctk.model.GrayScale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


@SessionScoped
public class GrayScaleReadFile implements Serializable {

    @Inject
    private Settings settings;

    @Inject
    private GrayScale grayScale;

    public void loadGrayScaleFile() {
        String line = null;
        BufferedReader reader = null;

        grayScale.setGrayScale("000");

        try {

            reader = Files.newBufferedReader(settings.getPathGrayScaleInfo(), StandardCharsets.UTF_8);

            line = reader.readLine();

            if (!line.isEmpty()) {
                if (line.matches("[0-9]{0,3}")) {
                    int testValue = Integer.parseInt(line);
                    if (testValue >= 0 && testValue <= 100) {
                        grayScale.setGrayScale(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
