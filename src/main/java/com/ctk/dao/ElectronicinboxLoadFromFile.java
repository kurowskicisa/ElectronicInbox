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

    public void loadData() {

        String line = null;
        Reader reader = null;

        try {

            reader = Files.newBufferedReader(settings.getPathLESPcsv(), StandardCharsets.UTF_8);
            line = ((BufferedReader) reader).readLine();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (!line.isEmpty()) {

            try {
                readingLESPLinesFromFile(line, (BufferedReader) reader);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readingLESPLinesFromFile(String line, BufferedReader reader) throws IOException {

        while (line != null) {

            if (!line.equals("")) {
                List<String> tempList = Arrays.asList(line.split(","));

                electronicInboxDao.setList(new ElectronicInbox(
                        tempList.get(0).trim(),
                        tempList.get(1).trim(),
                        tempList.get(2).trim(),
                        tempList.get(3).trim(),
                        tempList.get(4).trim(),
                        tempList.get(5).trim()));
            }

            line = reader.readLine();
        }
    }
}
