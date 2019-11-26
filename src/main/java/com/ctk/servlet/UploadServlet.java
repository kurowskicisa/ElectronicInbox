package com.ctk.servlet;

import com.ctk.dao.DataBase;
import com.ctk.dao.Settings;
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

            String stringDate = settings.getDateDataBaseInfoDate();

            stringDate = stringDate.substring(0, 4)
                    .concat("-")
                    .concat(stringDate.substring(4, 6))
                    .concat("-")
                    .concat(stringDate.substring(6, 8));

            if (stringDate.equals(strDate)) {
                APPLOGGER.info("Update not necessary");
            } else {
                APPLOGGER.info("Update is necessary");

                if (dataBase.isEPUAPAvailable()) {
                    dataBase.renameFileLESP();

                    dataBase.downloadfileLESP(fileSource);

                    dataBase.setDataBaseDateUpdate(strDate);

                    dataBase.setDataBaseRecordsCounter(settings.countTotalRercords().toString());

                    dataBase.updateDateDataBaseInfo();
                    dataBase.loadDataBaseInfo();
                    APPLOGGER.info("Database is updated");
                } else {
                    APPLOGGER.info("No connection to EPUAP");
                }
            }
        } else {
            APPLOGGER.info("No file: databaseinfo");
        }
        if (!resp.isCommitted()) {
            resp.sendRedirect("/electronicinbox/statistics");

        }
    }
}
