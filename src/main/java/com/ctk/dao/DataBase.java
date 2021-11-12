package com.ctk.dao;

import javax.enterprise.context.SessionScoped;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

@SessionScoped
public class DataBase extends com.ctk.model.DataBase implements Serializable {

    public void renameFileLESP() {
        String patch;
        Settings settings = new Settings();
        patch = String.valueOf(settings.getPathLESPcsv());

        File fileSource = new File(patch);
        File fileTarget = new File(patch.replace(".csv", "_" + settings.getDateDataBaseInfoDate() + ".csv"));

        if (!fileTarget.isFile()) {
            fileSource.renameTo(fileTarget);
        }
    }

    public void downloadfileLESP(File fileSource) {
        URL url = null;
        try {
            url = new
                    URL("https://epuap.gov.pl/LESP/LESP.csv");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedInputStream bufferedInputStream = null;
        try {
            if (url != null) {
                bufferedInputStream = new BufferedInputStream(url.openStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(fileSource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int count = 0;
        byte[] b1 = new byte[1024];

        while (true) {
            try {
                if (!((count = bufferedInputStream.read(b1)) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stream.write(b1, 0, count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isEPUAPAvailable() {
        try {
            final URL url = new URL("https://epuap.gov.pl");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
}
