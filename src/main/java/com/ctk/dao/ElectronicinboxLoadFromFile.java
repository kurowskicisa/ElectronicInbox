package com.ctk.dao;

import com.ctk.model.ElectronicInbox;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@RequestScoped
public class ElectronicinboxLoadFromFile {

    @Inject
    private Settings settings = new Settings();

    @Inject
    private ElectronicInboxDao electronicInboxDao;

    public void loadData(int page) {

        String line = null;
        Reader reader = null;

        try {

            reader = Files.newBufferedReader(settings.getPathLESPcsv(), StandardCharsets.UTF_8);
            line = ((BufferedReader) reader).readLine();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (line != null && !line.isEmpty()) {

            try {
                readingLESPLinesFromFile(line, (BufferedReader) reader, page);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readingLESPLinesFromFile(String line, BufferedReader reader, int page) throws IOException {

        double dataCounter = 0;
      //  int pageCounter = 0;

       // if (pageCounter <= 5) {

            while (line != null) {
                if (dataCounter >= 1 + (5 * page) && dataCounter <= 5 + (5 * page)) {
                    if (!line.equals("")) {
                        List<String> tempList = Arrays.asList(line.split(","));

                        electronicInboxDao.setList(new ElectronicInbox(
                                tempList.get(0).trim().replace("\"", ""),
                                tempList.get(1).trim().replace("\"", ""),
                                tempList.get(2).trim().replace("\"", ""),
                                tempList.get(3).trim().replace("\"", ""),
                                tempList.get(4).trim().replace("\"", ""),
                                tempList.get(5).trim().replace("\"", "")));
                    }
                }
                dataCounter++;
                line = reader.readLine();
        //    }
        }
    }
}
