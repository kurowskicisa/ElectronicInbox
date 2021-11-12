package com.ctk.dao;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class Settings extends com.ctk.model.Settings implements Serializable {

    private static final int FIELD_DATABASE_DATE_UPDATE = 0;
    private static final int FIELD_DATABASE_RECORDS_COUNTER = 1;

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

    public String monthToPolish(String dateToConvert) {

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
                datePolish = " kwietnia ";
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

    public void createGrayScaleFile(String digit) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(getPathGrayScaleInfo())))) {
            writer.append(digit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDefaultDatabaseInfoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(getPathDatabaseInfo())))) {
            writer.append("2000-01-01").append(";").append("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkGrayScaleValue() {

        String line;
        BufferedReader reader;

        try {

            reader = Files.newBufferedReader(getPathGrayScaleInfo(), StandardCharsets.UTF_8);

            line = Optional.ofNullable(reader.readLine()).orElse("");

            reader.close();

            if (line.isEmpty()) {
                createGrayScaleFile("000");
            }

            if (line.matches("[0-9]{0,3}")) {
                int testValue = Integer.parseInt(line);
                if (!(testValue >= 0 && testValue <= 100)) {
                    createGrayScaleFile("000");
                } else {
                    if (line.length() != 3) {
                        createGrayScaleFile(String.format("%03d", testValue));
                    }
                }
            } else {
                createGrayScaleFile("000");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkDatabaseInfo() {

        String line;
        BufferedReader reader;

        try {

            reader = Files.newBufferedReader(getPathDatabaseInfo(), StandardCharsets.UTF_8);

            line = Optional.ofNullable(reader.readLine()).orElse("");

            reader.close();

            if (line.isEmpty()) {
                createDefaultDatabaseInfoFile();
            }

            if (!line.isEmpty()) {
                List<String> tempList = Arrays.asList(line.split(";"));
                if (tempList.size() == 2) {
                    if (tempList.get(FIELD_DATABASE_DATE_UPDATE).isEmpty()
                            || tempList.get(FIELD_DATABASE_RECORDS_COUNTER).isEmpty()) {
                        createDefaultDatabaseInfoFile();
                    } else {
                        if (!tempList.get(FIELD_DATABASE_RECORDS_COUNTER).matches("^[0-9]+$")) {
                            createDefaultDatabaseInfoFile();
                        }

                        if (!tempList.get(FIELD_DATABASE_DATE_UPDATE)
                                .matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")) {
                            createDefaultDatabaseInfoFile();
                        }
                    }
                } else {
                    createDefaultDatabaseInfoFile();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
