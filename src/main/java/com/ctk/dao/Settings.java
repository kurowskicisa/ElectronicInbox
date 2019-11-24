package com.ctk.dao;

import com.ctk.model.DataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import java.util.Optional;

import static java.lang.String.valueOf;

@ApplicationScoped
public class Settings {

    @Inject
    private DataBase dataBase;

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.dao.Settings.class.getName());

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

    public boolean isLESPcsvFile() {
        String patch;

        patch = String.valueOf(getPathLESPcsv());

        File fileLESPcsv = new File(patch);

        return fileLESPcsv.isFile();
    }

    public boolean isAdminFile() {
        String patch;

        patch = String.valueOf(getPathAdmin());

        File fileAdmin = new File(patch);

        return fileAdmin.isFile();
    }

    public boolean isDataBaseInfoFile() {
        String patch;

        patch = String.valueOf(getPathDatabaseInfo());

        File fileDataBaseInfo = new File(patch);

        return fileDataBaseInfo.isFile();
    }

    public boolean isGrayScaleFile() {
        String patch;

        patch = String.valueOf(getPathGrayScaleInfo());

        File fileGrayScaleInfo = new File(patch);

        return fileGrayScaleInfo.isFile();
    }

    private boolean isDataBaseInfoFileEmpty() {
        BufferedReader reader;
        String line;

        if (isDataBaseInfoFile()) {

            try {
                reader = Files.newBufferedReader(getPathDatabaseInfo(), StandardCharsets.UTF_8);

                line = Optional.ofNullable(reader.readLine()).orElse("");

                return line.isEmpty();

            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public String getDateDataBaseInfoDate() {

        String dateDatabaseInfoDate;

        dateDatabaseInfoDate = "";
        String line;
        BufferedReader reader;

        try {

            reader = Files.newBufferedReader(getPathDatabaseInfo(), StandardCharsets.UTF_8);

            line = Optional.ofNullable(reader.readLine()).orElse("");

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

    public void loadDataBaseInfo() {
        String line;

        BufferedReader reader;

        if (new File(valueOf(getPathDatabaseInfo())).isFile()) {

            APPLOGGER.info("File " + getPathDatabaseInfo().toString() + " exist");

            try {

                reader = Files.newBufferedReader(getPathDatabaseInfo(), StandardCharsets.UTF_8);

                APPLOGGER.info("File: try read...");

                if (!isDataBaseInfoFileEmpty()) {

                    APPLOGGER.info("File is not empty");

                    line = reader.readLine();

                    APPLOGGER.info(line);

                    if (!line.isEmpty()) {
                        List<String> tempList = Arrays.asList(line.split(";"));

                        if (tempList.get(FIELD_DATABASE_DATE_UPDATE)
                                .matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) {
                            dataBase.setDataBaseDateUpdate(monthToPolish(tempList.get(FIELD_DATABASE_DATE_UPDATE)));
                            dataBase.setDataBaseRecordsCounter(tempList.get(FIELD_DATABASE_RECORDS_COUNTER));
                            APPLOGGER.info("File structure is OK");
                        }
                    } else {
                        dataBase.setDataBaseDateUpdate("-");
                        dataBase.setDataBaseRecordsCounter("-");
                        APPLOGGER.info("File structure is NOT OK");
                    }
                } else {
                    dataBase.setDataBaseDateUpdate("-");
                    dataBase.setDataBaseRecordsCounter("-");
                    APPLOGGER.info("File is empty");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            dataBase.setDataBaseDateUpdate("-");
            dataBase.setDataBaseRecordsCounter("-");
            APPLOGGER.info("File " + getPathDatabaseInfo().toString() + " do not exist");
        }
    }

    private String monthToPolish(String dateToConvert) {

        String datePolish = "";

        switch (dateToConvert.substring(5, 7).trim()) {
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

        return dateToConvert.substring(8, 10).concat(datePolish).concat(dateToConvert.substring(0, 4));
    }

    public void createGrayScaleFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(getPathGrayScaleInfo())))) {
            writer.append("000");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDatabaseInfoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(getPathDatabaseInfo())))) {
            writer.append("2000-01-01;0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkValue() {

        String line;
        BufferedReader reader;


        try {

            reader = Files.newBufferedReader(getPathGrayScaleInfo(), StandardCharsets.UTF_8);

            line = Optional.ofNullable(reader.readLine()).orElse("");

            if (line.isEmpty()) {
                reader.close();
                createGrayScaleFile();
            }

            if (!line.matches("[0-9]{0,3}")) {
                reader.close();
                createGrayScaleFile();
            }

            int testValue = Integer.parseInt(line);
            if (!(testValue >= 0 && testValue <= 100)) {
                reader.close();
                createGrayScaleFile();
            }
            
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
