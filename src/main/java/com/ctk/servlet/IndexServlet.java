package com.ctk.servlet;

import com.ctk.dao.GrayScaleReadFile;
import com.ctk.dao.Settings;
import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import com.ctk.model.DataBase;
import com.ctk.model.GrayScale;
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
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.time.LocalTime.now;

@RequestScoped
@WebServlet(urlPatterns = { "", "/", "/login"})
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
    private GrayScaleReadFile grayScaleReadFile;

    @Inject
    private DataBase dataBase;


    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.servlet.IndexServlet.class.getName());

    @Override
    public void init() {
        APPLOGGER.info("init()] | ");

        if (!settings.isAdminFile()){
            APPLOGGER.info("No file: " + settings.getPathAdmin());
        }

        if (!settings.isLESPcsvFile()){
            APPLOGGER.info("No file: "+ settings.getPathLESPcsv());
        }

        if (!settings.isDataBaseInfoFile()){
            APPLOGGER.info("No file: " + settings.getPathDatabaseInfo());
        }

        if (!settings.isGrayScaleFile()) {
            APPLOGGER.info("No file: " + settings.getPathGrayScaleInfo());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APPLOGGER.info("[doGet()] | ");

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

        grayScaleReadFile.loadGrayScaleFile();
        modelGeneratorTemplate.setModel("grayScale_",
                grayScale.getGrayScale());

        settings.loadDataBaseInfo();
        modelGeneratorTemplate.setModel("dataBaseDateUpdate_",
                dataBase.getDataBaseDateUpdate() );
        modelGeneratorTemplate.setModel("dataBaseRecordsCounter_",
                dataBase.getDataBaseRecordsCounter() );

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
