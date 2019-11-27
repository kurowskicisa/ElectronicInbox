package com.ctk.servlet;

import com.ctk.dao.GrayScale;
import com.ctk.dao.DataBase;
import com.ctk.dao.Statistic;
import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.SessionScoped;
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

@SessionScoped
@WebServlet(urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    private Statistic statistic;

    @Inject
    private GrayScale grayScale;

    @Inject
    private DataBase dataBase;

    private static Logger APPLOGGER = LogManager.getLogger(StatisticsServlet.class.getName());

    @Override
    public void init() {

        APPLOGGER.info("init() ");
        grayScale.loadGrayScaleFile();
        modelGeneratorTemplate.setModel("grayScale_",
                grayScale.getGrayScale());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APPLOGGER.info("doGet() ");

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8\"");

        dataBase.loadDataBaseInfo();
        modelGeneratorTemplate.setModel("dataBaseDateUpdate_",
                dataBase.getDataBaseDateUpdate());

        statistic.loadFileESP();

        modelGeneratorTemplate.setModel("nameLengthMin_",
                statistic.getNameLengthMin());

        modelGeneratorTemplate.setModel("nameLengthMax_",
                statistic.getNameLengthMax());

        modelGeneratorTemplate.setModel("nameCounterEmpty_",
                statistic.getNameCounterEmpty());

        modelGeneratorTemplate.setModel("regonLengthMin_",
                statistic.getRegonLengthMin());

        modelGeneratorTemplate.setModel("regonCounterEmpty_",
                statistic.getRegonCounterEmpty());

        modelGeneratorTemplate.setModel("regonLengthMax_",
                statistic.getRegonLengthMax());

        modelGeneratorTemplate.setModel("addressLengthMin_",
                statistic.getAddressLengthMin());

        modelGeneratorTemplate.setModel("addressCounterEmpty_",
                statistic.getAddressCounterEmpty());

        modelGeneratorTemplate.setModel("addressLengthMax_",
                statistic.getAddressLengthMax());

        modelGeneratorTemplate.setModel("zipLengthMin_",
                statistic.getZipLengthMin());

        modelGeneratorTemplate.setModel("zipCounterEmpty_",
                statistic.getZipCounterEmpty());

        modelGeneratorTemplate.setModel("zipLengthMax_",
                statistic.getZipLengthMax());

        modelGeneratorTemplate.setModel("placeLengthMin_",
                statistic.getPlaceLengthMin());

        modelGeneratorTemplate.setModel("placeCounterEmpty_",
                statistic.getPlaceCounterEmpty());

        modelGeneratorTemplate.setModel("placeLengthMax_",
                statistic.getPlaceLengthMax());

        modelGeneratorTemplate.setModel("uriLengthMin_",
                statistic.getUriLengthMin());

        modelGeneratorTemplate.setModel("uriCounterEmpty_",
                statistic.getUriCounterEmpty());

        modelGeneratorTemplate.setModel("uriLengthMax_",
                statistic.getUriLengthMax());

        modelGeneratorTemplate.setModel("totalRecords_",
                statistic.getTotalRecords());

        modelGeneratorTemplate.setModel("dataErrorRegonCounter_",
                statistic.getDataErrorRegonCounter());

        modelGeneratorTemplate.setModel("dataErrorZipCounter_",
                statistic.getDataErrorZipCounter());

        modelGeneratorTemplate.setModel("dataEmptyRegonCounter_",
                statistic.getDataEmptyRegonCounter());

        modelGeneratorTemplate.setModel("dataEmptyZipCounter_",
                statistic.getDataEmptyZipCounter());

        try {
            Template template = templateProvider.getTemplate(getServletContext(), "statistics");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());
            APPLOGGER.info("[statistics: WEB loaded] |");

        } catch (TemplateException e) {
            e.printStackTrace();
            APPLOGGER.info("[statistics: WEB NOT loaded] |");
        }

        LocalTime stopDoGet = now();

        APPLOGGER.info("[statistics: time of action (milliseconds)] | "
                + (ChronoUnit.NANOS.between(startDoGet, stopDoGet)) / 1000000);
    }
}
