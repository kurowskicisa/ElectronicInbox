package com.ctk.dao;

import com.ctk.model.DataBase;

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
public class DataBaseInfo implements Serializable {

    @Inject
    private Settings settings;

    @Inject
    private DataBase dataBase;

    private static final int FIELD_DATABASE_DATE_UPDATE = 0;
    private static final int FIELD_DATABASE_RECORDS_COUNTER = 1;

    public void loadDataBaseInfo() {
        String line;
        BufferedReader reader;

        try {

            reader = Files.newBufferedReader(settings.getPathDatabaseInfo(), StandardCharsets.UTF_8);

            line = reader.readLine();

            if (!line.isEmpty()) {
                List<String> tempList = Arrays.asList(line.split(";"));

                dataBase.setDataBaseDateUpdate(monthToPolish(tempList.get(FIELD_DATABASE_DATE_UPDATE)));
                dataBase.setDataBaseRecordsCounter(tempList.get(FIELD_DATABASE_RECORDS_COUNTER));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String monthToPolish(String dateToConvert) {

        String datePolish = "";

        switch (dateToConvert.substring(4, 6).trim()) {
            case "01":
                datePolish = " stycznia ";
                break;
            case "02":
                datePolish = " lutego ";
                break;
            case "03":
                datePolish = " marca ";
                break;
            case "04":
                datePolish = " kwoetnia ";
                break;
            case "05":
                datePolish = " maja ";
                break;
            case "06":
                datePolish = " czerwca ";
                break;
            case "07":
                datePolish = " lipca ";
                break;
            case "08":
                datePolish = " sierpnia ";
                break;
            case "09":
                datePolish = " września ";
                break;
            case "10":
                datePolish = " października ";
                break;
            case "11":
                datePolish = " listopada ";
                break;
            case "12":
                datePolish = " grudnia ";
                break;
        }

        return dateToConvert.substring(6, 8).concat(datePolish).concat(dateToConvert.substring(0, 4));
    }

}
