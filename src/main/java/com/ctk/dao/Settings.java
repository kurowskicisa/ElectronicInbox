package com.ctk.dao;

import com.ctk.model.DataBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class Settings {

    @Inject
    private DataBase dataBase;

    private final Path pathLESPcsv = Paths.get(System.getProperty("jboss.server.data.dir"), "LESP.csv");
    private final Path pathAdmin = Paths.get(System.getProperty("jboss.server.data.dir"), "admin.csv");
    private final Path pathDatabaseInfo = Paths.get(System.getProperty("jboss.server.data.dir"), "databaseinfo.csv");
    private final Path pathGrayScaleInfo = Paths.get(System.getProperty("jboss.server.data.dir"), "grayscale.csv");

    private static final int FIELD_DATABASE_DATE_UPDATE = 0;
    private static final int FIELD_DATABASE_RECORDS_COUNTER = 1;


    public Path getPathLESPcsv() {
        return pathLESPcsv;
    }

    public Path getPathAdmin() {
        return pathAdmin;
    }

    public Path getPathDatabaseInfo() {
        return pathDatabaseInfo;
    }


    public Path getPathGrayScaleInfo() {
        return pathGrayScaleInfo;
    }


    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateTimeFormatter getDateFormat() {
        return dateFormat;
    }

    public boolean isDataBaseInfoFile() {
        String patch;

        patch = String.valueOf(getPathDatabaseInfo());

        File fileDataBaseInfo = new File(patch);

        return fileDataBaseInfo.isFile();

    }

    public String getDateDataBaseInfoDate() {

        String dateDatabaseInfoDate;

        dateDatabaseInfoDate = "";
        String line;
        BufferedReader reader;

        try {

            reader = Files.newBufferedReader(getPathDatabaseInfo(), StandardCharsets.UTF_8);

            line = reader.readLine();

            if (!line.isEmpty()) {
                List<String> tempList = Arrays.asList(line.split(";"));

                dateDatabaseInfoDate = tempList.get(FIELD_DATABASE_DATE_UPDATE);
                dateDatabaseInfoDate = dateDatabaseInfoDate.replace("-", "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dateDatabaseInfoDate;
    }

    public String getDateDataBaseInfoRecordCounter() {

        String dateDatabaseInfoRecordCounter;

        dateDatabaseInfoRecordCounter = "";
        String line;
        BufferedReader reader;

        try {

            reader = Files.newBufferedReader(getPathDatabaseInfo(), StandardCharsets.UTF_8);

            line = reader.readLine();

            if (!line.isEmpty()) {
                List<String> tempList = Arrays.asList(line.split(";"));

                dateDatabaseInfoRecordCounter = tempList.get(FIELD_DATABASE_RECORDS_COUNTER);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dateDatabaseInfoRecordCounter;
    }

    public Integer countTotalRercords() throws IOException {
        String line = null;

        BufferedReader reader = null;

        Integer numberLines = 0;

        try {
            reader = Files.newBufferedReader(getPathLESPcsv(), StandardCharsets.UTF_8);

            line = reader.readLine();
            line = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (line != null) {

            if (!line.equals("")) {
                numberLines++;

            }
            line = reader.readLine();

        }

        return numberLines;
    }

    public void updateDateDataBaseInfo() throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(getPathDatabaseInfo())))) {
            writer.append(dataBase.getDataBaseDateUpdate());
            writer.append(";");
            writer.append(dataBase.getDataBaseRecordsCounter());
        }

    }
}
