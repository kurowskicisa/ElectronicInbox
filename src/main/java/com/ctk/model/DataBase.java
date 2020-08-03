package com.ctk.model;

import com.ctk.dao.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

public class DataBase implements com.ctk.services.DataBase, Serializable {
    private String dataBaseDateUpdate;
    private String dataBaseRecordsCounter;


    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.dao.DataBase.class.getName());

    private static final int FIELD_DATABASE_DATE_UPDATE = 0;
    private static final int FIELD_DATABASE_RECORDS_COUNTER = 1;

    public DataBase() {
    }

    public String getDataBaseDateUpdate() {
        return dataBaseDateUpdate;
    }

    public void setDataBaseDateUpdate(String dataBaseDateUpdate) {
        this.dataBaseDateUpdate = dataBaseDateUpdate;
    }

    public String getDataBaseRecordsCounter() {
        return dataBaseRecordsCounter;
    }

    public void setDataBaseRecordsCounter(String dataBaseRecordsCounter) {
        this.dataBaseRecordsCounter = dataBaseRecordsCounter;
    }

    public void updateDateDataBaseInfo() throws IOException {

        Settings settings = new Settings();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(settings.getPathDatabaseInfo())))) {
            writer.append(getDataBaseDateUpdate());
            writer.append(";");
            writer.append(getDataBaseRecordsCounter());
        }
    }

    public void loadDataBaseInfo() {
        String line;

        BufferedReader reader;

        Settings settings = new Settings();

        if (new File(valueOf(settings.getPathDatabaseInfo())).isFile()) {

            APPLOGGER.info("File " + settings.getPathDatabaseInfo().toString() + " exist");

            try {

                reader = Files.newBufferedReader(settings.getPathDatabaseInfo(), StandardCharsets.UTF_8);

                APPLOGGER.info("File: try read...");

                if (!isDataBaseInfoFileEmpty()) {

                    APPLOGGER.info("File is not empty");

                    line = reader.readLine();

                    APPLOGGER.info(line);

                    if (!line.isEmpty()) {
                        List<String> tempList = Arrays.asList(line.split(";"));

                        if (tempList.get(FIELD_DATABASE_DATE_UPDATE)
                                .matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) {
                            setDataBaseDateUpdate(settings.monthToPolish(tempList.get(FIELD_DATABASE_DATE_UPDATE)));
                            setDataBaseRecordsCounter(tempList.get(FIELD_DATABASE_RECORDS_COUNTER));
                            APPLOGGER.info("File structure is OK");
                        }
                    } else {
                        setDataBaseDateUpdate("-");
                        setDataBaseRecordsCounter("-");
                        APPLOGGER.info("File structure is NOT OK");
                    }
                } else {
                    setDataBaseDateUpdate("-");
                    setDataBaseRecordsCounter("-");
                    APPLOGGER.info("File is empty");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            setDataBaseDateUpdate("-");
            setDataBaseRecordsCounter("-");
            APPLOGGER.info("File " + settings.getPathDatabaseInfo().toString() + " do not exist");
        }
    }

    public boolean isDataBaseInfoFileEmpty() {
        BufferedReader reader;
        String line;

        Settings settings = new Settings();

        if (settings.isDataBaseInfoFile()) {

            try {
                reader = Files.newBufferedReader(settings.getPathDatabaseInfo(), StandardCharsets.UTF_8);

                line = Optional.ofNullable(reader.readLine()).orElse("");

                return line.isEmpty();

            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
