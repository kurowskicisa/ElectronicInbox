package com.ctk.servlet;

import com.ctk.dao.Settings;
import com.ctk.model.DataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RequestScoped
@WebServlet(urlPatterns = {"/upload"})
public class UploadServlet extends HttpServlet {

    @Inject
    private Settings settings;

    @Inject
    private DataBase dataBase;

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.servlet.UploadServlet.class.getName());

    @Override
    public void init() {
        APPLOGGER.info("init()] | ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APPLOGGER.info("[doGet()] | ");

        Date dateToday = Calendar.getInstance().getTime();

        // https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(dateToday);

        APPLOGGER.info(settings.getPathDatabaseInfo());

        if (settings.isDataBaseInfoFile()) {

            String patch;
            patch = String.valueOf(settings.getPathLESPcsv());
            File fileSource = new File(patch);

            if (settings.getDateDataBaseInfoDate().equals(strDate)) {
                APPLOGGER.info("Update not necessary");
            } else {
                APPLOGGER.info("Update is necessary");

                if (isEPUAPAvailable()) {
                    renameFileLESP();

                    downloadfileLESP(fileSource);

                    dataBase.setDataBaseDateUpdate(strDate);
                    dataBase.setDataBaseRecordsCounter(settings.countTotalRercords().toString());

                    settings.updateDateDataBaseInfo();
                    settings.loadDataBaseInfo();
                    APPLOGGER.info("Database is updated");
                } else {
                    APPLOGGER.info("No connection to EPUAP");
                }
            }
        } else {
            APPLOGGER.info("No file: databaseinfo");
        }
    }

    private void renameFileLESP() {
        String patch;

        patch = String.valueOf(settings.getPathLESPcsv());

        File fileSource = new File(patch);
        File fileTarget = new File(patch.replace(".csv", "_" + settings.getDateDataBaseInfoDate() + ".csv"));

        if (!fileTarget.isFile()) {
            fileSource.renameTo(fileTarget);
        }
    }

    private void downloadfileLESP(File fileSource) {
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

    private static boolean isEPUAPAvailable() {
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
