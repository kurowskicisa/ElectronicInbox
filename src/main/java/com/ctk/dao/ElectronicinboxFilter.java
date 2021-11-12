package com.ctk.dao;

import com.ctk.model.ElectronicInbox;
import com.ctk.model.ElectronicInboxFilter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.Arrays;
import java.util.List;

@RequestScoped
public class ElectronicinboxFilter implements Serializable {

    @Inject
    Settings settings = new Settings();

    @Inject
    ElectronicInboxDao electronicInboxDao;

    @Inject
    ElectronicInboxFilter electronicInboxFilter;

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

        if (new File(String.valueOf(settings.getPathLESPcsv())).isFile()) {

            try {

                reader = Files.newBufferedReader(settings.getPathLESPcsv(), StandardCharsets.UTF_8);
                line = reader.readLine();

                line = reader.readLine();

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (line != null && !line.isEmpty()) {

                try {
                    readFileFiltered(line, reader);
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readFileFiltered(
            String line,
            BufferedReader reader)
            throws IOException {

        double pagesCounter;
        double dataCounter = 0.00;
        double dataTotalCounter = 0.00;
        double currentPageN;

        String currentPage = electronicInboxFilter.getPage();
        String name = electronicInboxFilter.getName();
        String address = electronicInboxFilter.getAddress();
        String place = electronicInboxFilter.getPlace();

        if (currentPage.isEmpty()) {
            currentPageN = 1;
        } else {
            if (currentPage.matches("[0-9]*")) {
                currentPageN = Integer.parseInt(currentPage);
            } else {
                currentPageN = 1;
            }
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
                        electronicInboxDao.setList(new ElectronicInbox(
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

        electronicInboxFilter.setTotalPages((int) pagesCounter);
        electronicInboxFilter.setTotalFilteredRecords(dataCounter);
        electronicInboxFilter.setTotalRecords(dataTotalCounter);
    }
}
