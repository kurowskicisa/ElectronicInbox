package com.ctk.dao;

import com.ctk.model.ElectronicInboxImpl;
import com.ctk.model.ElectronicInboxFilterFile;

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
public class ElectronicinboxLoadFromFileFiltered implements Serializable {

    @Inject
    private Settings settings = new Settings();

    @Inject
    private ElectronicInboxDao electronicInboxDao;

    @Inject
    private ElectronicInboxFilterFile electronicInboxFilterFile;

    private static final int RECORDS_ON_PAGE = 5;
    private static final int FIELD_NAME = 0;
    private static final int FIELD_REGON = 1;
    private static final int FIELD_ADDRESS = 2;
    private static final int FIELD_ZIP = 3;
    private static final int FIELD_PLACE = 4;
    private static final int FIELD_URI = 5;

    public void loadData() {
        String line = null;
        BufferedReader reader = null;

        try {

            reader = Files.newBufferedReader(settings.getPathLESPcsv(), StandardCharsets.UTF_8);
            line = reader.readLine();

            line = reader.readLine();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (line != null && !line.isEmpty()) {

            try {
                readingLESPLinesFromFileFiltered(line, reader);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readingLESPLinesFromFileFiltered(
            String line,
            BufferedReader reader)
            throws IOException {

        double pagesCounter = 0.00;
        double dataCounter = 0.00;
        double dataTotalCounter = 0.00;
        Integer currentPageN = 0;

        String currentPage = electronicInboxFilterFile.getPage();
        String name = electronicInboxFilterFile.getName();
        String address = electronicInboxFilterFile.getAddress();
        String place = electronicInboxFilterFile.getPlace();

        if (currentPage.isEmpty()) {
            currentPageN = 1;
        } else {
            currentPageN = Integer.parseInt(currentPage);
        }

        String nameToCompare;
        String addressToCompare;
        String placeToCompare;

        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(","));

                dataTotalCounter++;

                nameToCompare = tempList.get(FIELD_NAME).trim().replace("\"", "").toLowerCase();
                name = name.toLowerCase();

                addressToCompare = tempList.get(FIELD_ADDRESS).trim().replace("\"", "").toLowerCase();
                address = address.toLowerCase();

                placeToCompare = tempList.get(FIELD_PLACE).trim().replace("\"", "").toLowerCase();
                place = place.toLowerCase();

                if (nameToCompare.contains(name)
                        && placeToCompare.contains(place)
                        && addressToCompare.contains(address)) {
                    dataCounter++;

                    if (dataCounter >= 1 + (RECORDS_ON_PAGE * currentPageN) - RECORDS_ON_PAGE
                            && dataCounter <= (RECORDS_ON_PAGE * currentPageN)) {
                        electronicInboxDao.setList(new ElectronicInboxImpl(
                                tempList.get(FIELD_NAME).trim().replace("\"", ""),
                                tempList.get(FIELD_REGON).trim().replace("\"", ""),
                                tempList.get(FIELD_ADDRESS).trim().replace("\"", ""),
                                tempList.get(FIELD_ZIP).trim().replace("\"", ""),
                                tempList.get(FIELD_PLACE).trim().replace("\"", ""),
                                tempList.get(FIELD_URI).trim().replace("\"", "")));
                    }
                }
            }

            line = reader.readLine();
        }

        pagesCounter = (dataCounter / RECORDS_ON_PAGE);

        if (dataCounter % RECORDS_ON_PAGE != 0) {
            pagesCounter++;
        }

        electronicInboxFilterFile.setTotalPages((int) pagesCounter);
        electronicInboxFilterFile.setTotalFilteredRecords(dataCounter);
        electronicInboxFilterFile.setTotalRecords(dataTotalCounter);
    }
}
