package com.ctk.dao;

import com.ctk.model.ElectronicInbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class ElectronicinboxLoadFromFile {

    private Settings settings = new Settings();
    private ElectronicInboxDao electronicInboxDao;

    private String line;
    private Reader reader;

    public void loadData() {
        try {
            reader = Files.newBufferedReader(settings.getPathLESPcsv(), StandardCharsets.UTF_8);
        } catch(IOException e1) {
            e1.printStackTrace();
        }
        try {
            line = ((BufferedReader) reader).readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        if(line != null) {

            try {
                readingLESPLinesFromFile(line, (BufferedReader) reader);
                reader.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readingLESPLinesFromFile(String line, BufferedReader reader) throws IOException {
        while(line != null) {
            if(!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(","));

                electronicInboxDao.setList(new ElectronicInbox(
                        tempList.get(1).trim(),
                        tempList.get(2).trim(),
                        tempList.get(3).trim(),
                        tempList.get(4).trim(),
                        tempList.get(5).trim(),
                        tempList.get(6).trim()));
            }
            line = reader.readLine();
        }
    }
}
