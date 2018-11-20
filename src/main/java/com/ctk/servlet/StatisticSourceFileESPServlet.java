package com.ctk.servlet;


import com.ctk.dao.StatisticSourceFileESPReadFile;
import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import com.ctk.model.StatisticSourceFileESP;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

@WebServlet(urlPatterns = "/statistics")
public class StatisticSourceFileESPServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    private StatisticSourceFileESP statisticSourceFileESP;

    @Inject
    private StatisticSourceFileESPReadFile statisticSourceFileESPReadFile;

    private static Logger APPLOGGER = LogManager.getLogger(StatisticSourceFileESPServlet.class.getName());

    @Override
    public void init() {
        APPLOGGER.info("|statistics: init() ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8");

        statisticSourceFileESPReadFile.loadFileESP();

        modelGeneratorTemplate.setModel("nameLengthMin_",
                statisticSourceFileESP.getNameLengthMin());

        modelGeneratorTemplate.setModel("nameLengthMax_",
                statisticSourceFileESP.getNameLengthMax());

        modelGeneratorTemplate.setModel("nameCounterEmpty_",
                statisticSourceFileESP.getNameCounterEmpty());

        modelGeneratorTemplate.setModel("regonLengthMin_",
                statisticSourceFileESP.getRegonLengthMin());

        modelGeneratorTemplate.setModel("regonCounterEmpty_",
                statisticSourceFileESP.getRegonCounterEmpty());

        modelGeneratorTemplate.setModel("regonLengthMax_",
                statisticSourceFileESP.getRegonLengthMax());

        modelGeneratorTemplate.setModel("addressLengthMin_",
                statisticSourceFileESP.getAddressLengthMin());

        modelGeneratorTemplate.setModel("addressCounterEmpty_",
                statisticSourceFileESP.getAddressCounterEmpty());

        modelGeneratorTemplate.setModel("addressLengthMax_",
                statisticSourceFileESP.getAddressLengthMax());

        modelGeneratorTemplate.setModel("zipLengthMin_",
                statisticSourceFileESP.getZipLengthMin());

        modelGeneratorTemplate.setModel("zipCounterEmpty_",
                statisticSourceFileESP.getZipCounterEmpty());

        modelGeneratorTemplate.setModel("zipLengthMax_",
                statisticSourceFileESP.getZipLengthMax());

        modelGeneratorTemplate.setModel("placeLengthMin_",
                statisticSourceFileESP.getPlaceLengthMin());

        modelGeneratorTemplate.setModel("placeCounterEmpty_",
                statisticSourceFileESP.getPlaceCounterEmpty());

        modelGeneratorTemplate.setModel("placeLengthMax_",
                statisticSourceFileESP.getPlaceLengthMax());

        modelGeneratorTemplate.setModel("uriLengthMin_",
                statisticSourceFileESP.getUriLengthMin());

        modelGeneratorTemplate.setModel("uriCounterEmpty_",
                statisticSourceFileESP.getUriCounterEmpty());

        modelGeneratorTemplate.setModel("uriLengthMax_",
                statisticSourceFileESP.getUriLengthMax());

        modelGeneratorTemplate.setModel("totalRecords_",
                statisticSourceFileESP.getTotalRecords());

        modelGeneratorTemplate.setModel("dataErrorRegonCounter_",
                statisticSourceFileESP.getDataErrorRegonCounter());

        modelGeneratorTemplate.setModel("dataErrorZipCounter_",
                statisticSourceFileESP.getDataErrorZipCounter());

        try {
            Template template = templateProvider.getTemplate(getServletContext(), "statistics");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());
            APPLOGGER.info("[statistics: WEB loaded] |");

        } catch(TemplateException e) {
            e.printStackTrace();
            APPLOGGER.info("[statistics: WEB NOT loaded] |");
        }

        LocalTime stopDoGet = now();

        APPLOGGER.info("[statistics: time of action (milliseconds)] | "
                + (ChronoUnit.NANOS.between(startDoGet, stopDoGet)) / 1000000);
    }

}
