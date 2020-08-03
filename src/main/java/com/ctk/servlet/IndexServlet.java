package com.ctk.servlet;

import com.ctk.dao.DataBase;
import com.ctk.dao.GrayScale;
import com.ctk.dao.Settings;
import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static java.time.LocalTime.now;

@RequestScoped
@WebServlet(urlPatterns = {"", "/", "/index"})
public class IndexServlet extends HttpServlet {

    @Inject
    private Settings settings;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    private GrayScale grayScale;

    @Inject
    private DataBase dataBase;

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.servlet.IndexServlet.class.getName());

    @Override
    public void init() {

        APPLOGGER.info("init()");

        Date dateToday = Calendar.getInstance().getTime();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(dateToday);

        if (settings.isGrayScaleFile()) {
            settings.checkGrayScaleValue();
        }

        if (!settings.isGrayScaleFile()) {
            APPLOGGER.info("No file: " + settings.getPathGrayScaleInfo());
            settings.createGrayScaleFile("000");
            if (settings.isGrayScaleFile()) {
                APPLOGGER.info("File: " + settings.getPathGrayScaleInfo() + " is created with default value");
            }
        }

        if (settings.isGrayScaleFile()) {
            settings.checkDatabaseInfo();
        }

        if (!settings.isDataBaseInfoFile()) {
            APPLOGGER.info("No file: " + settings.getPathDatabaseInfo());
            settings.createDefaultDatabaseInfoFile();
            if (settings.isDataBaseInfoFile()) {
                APPLOGGER.info("File: " + settings.getPathDatabaseInfo() + " is created with default values");
            }
        }

        if (!settings.isLESPcsvFile()) {
            APPLOGGER.info("No file: " + settings.getPathLESPcsv());
            if (dataBase.isEPUAPAvailable()) {
                dataBase.downloadfileLESP(new File(String.valueOf(settings.getPathLESPcsv())));
                if (settings.isLESPcsvFile()) {
                    APPLOGGER.info("File: " + settings.getPathLESPcsv() + " is created with downloaded values");
                }
                dataBase.setDataBaseDateUpdate(strDate);

                try {
                    dataBase.setDataBaseRecordsCounter(settings.countTotalRercords().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    dataBase.updateDateDataBaseInfo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!settings.isAdminFile()) {
            APPLOGGER.info("No file: " + settings.getPathAdmin());
            APPLOGGER.info("Use: eib_setup_to manage administrator");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APPLOGGER.info("doGet()");

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

        grayScale.loadGrayScaleFile();
        modelGeneratorTemplate.setModel("grayScale_",
                grayScale.getGrayScale());

        dataBase.loadDataBaseInfo();
        modelGeneratorTemplate.setModel("dataBaseDateUpdate_",
                dataBase.getDataBaseDateUpdate());
        modelGeneratorTemplate.setModel("dataBaseRecordsCounter_",
                dataBase.getDataBaseRecordsCounter());

        try {
            Template template = templateProvider.getTemplate(getServletContext(), "login");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());
            APPLOGGER.info("[IndexServlet.java | loaded] |");

        } catch (TemplateException e) {
            e.printStackTrace();
            APPLOGGER.info("[IndexServlet.java | NOT loaded] |");
        }

        LocalTime stopDoGet = now();

        APPLOGGER.info("IndexServlet | time of action (milliseconds)] | "
                + (ChronoUnit.NANOS.between(startDoGet, stopDoGet)) / 1000000);
    }

}
