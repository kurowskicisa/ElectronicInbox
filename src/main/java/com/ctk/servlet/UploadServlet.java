package com.ctk.servlet;

import com.ctk.dao.DataBaseInfo;
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
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RequestScoped
@WebServlet(urlPatterns = {"/upload"})
public class UploadServlet extends HttpServlet {

    @Inject
    private Settings settings;

    @Inject
    private DataBase dataBase;

    @Inject
    private DataBaseInfo dataBaseInfo;

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.servlet.UploadServlet.class.getName());

    @Override
    public void init() {
        APPLOGGER.info("init()] | ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APPLOGGER.info("[doGet()] | ");

        if (settings.isDataBaseInfoFile()){

        };

        downAndUpdateDataBase();


    }

    private void downAndUpdateDataBase() {
        Date date = Calendar.getInstance().getTime();

        // https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html

        DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        String strDate = dateFormat.format(date);

        String patch;

        patch = String.valueOf(settings.getPathLESPcsv());

        File fileSource = new File(patch);
        File fileTarget = new File(patch.replace(".csv", "_" + strDate + ".csv"));

        if (!fileTarget.isFile()) {
            fileSource.renameTo(fileTarget);
        }


        if (!fileSource.isFile()) {
            downloadfileLESP(fileSource);
//            uploadDatabaseInfo();
        }

        uploadDatabaseInfo();

        APPLOGGER.info("upload finished");
    }

    private void uploadDatabaseInfo() {

        Path databasePatch;

        databasePatch = settings.getPathDatabaseInfo();
        APPLOGGER.info(databasePatch);
        dataBaseInfo.loadDataBaseInfo();
        APPLOGGER.info("Data: "+Optional.ofNullable(dataBase.getDataBaseDateUpdate()).orElse("brak danych"));
        System.out.println("Data: "+Optional.ofNullable(dataBase.getDataBaseDateUpdate()).orElse("brak danych"));
        APPLOGGER.info("Rekordów: "+Optional.ofNullable(dataBase.getDataBaseRecordsCounter()).orElse("brak danych"));
        System.out.println("Rekordów: "+Optional.ofNullable(dataBase.getDataBaseRecordsCounter()).orElse("brak danych"));


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
            bufferedInputStream = new BufferedInputStream(url.openStream());
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

}
