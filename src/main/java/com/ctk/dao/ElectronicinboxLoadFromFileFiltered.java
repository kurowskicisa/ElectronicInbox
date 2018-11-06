package com.ctk.dao;

import com.ctk.model.ElectronicInbox;
import com.ctk.model.ElectronicInboxFilterFile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.Arrays;
import java.util.List;


@RequestScoped
public class ElectronicinboxLoadFromFileFiltered {

    @Inject
    private Settings settings = new Settings();

    @Inject
    private ElectronicInboxDao electronicInboxDao;

    @Inject
    private ElectronicInboxFilterFile electronicInboxFilterFile;

    double dataCounter = 0.00;
    double pagesCounter = 0;

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
            line =  reader.readLine();

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

        Integer currentPage = Integer.parseInt(electronicInboxFilterFile.getPage());
        String name = electronicInboxFilterFile.getName();
        String address = electronicInboxFilterFile.getAddress();
        String place = electronicInboxFilterFile.getPlace();

        String nameToCompare;
        String addressToCompare;
        String placeToCompare;

        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(","));

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

                    if (dataCounter >= 1 + (RECORDS_ON_PAGE * currentPage) - RECORDS_ON_PAGE
                            && dataCounter <= (RECORDS_ON_PAGE * currentPage)) {
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

        electronicInboxFilterFile.setTotalFilteredRecords(dataCounter);
        electronicInboxFilterFile.setTotalPages((int) pagesCounter);
    }
}
