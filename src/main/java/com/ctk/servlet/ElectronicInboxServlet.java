package com.ctk.servlet;

import com.ctk.dao.ElectronicInboxDao;
import com.ctk.dao.ElectronicinboxFilter;

import com.ctk.dao.GrayScale;
import com.ctk.model.ElectronicInboxFilter;

import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.time.LocalTime.now;

@RequestScoped
@WebServlet(urlPatterns = "/eib")
public class ElectronicInboxServlet extends HttpServlet {

    @Inject
    TemplateProvider templateProvider;

    @Inject
    ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    ElectronicinboxFilter electronicinboxFilter;

    @Inject
    ElectronicInboxDao electronicInboxDao;

    @Inject
    ElectronicInboxFilter electronicInboxFilter;

    @Inject
    GrayScale grayScale;

    final private static Logger APPLOGGER = LogManager.getLogger(ElectronicInboxServlet.class.getName());

    @Override
    public void init() {

        APPLOGGER.info("init() | ");

        resetFormFields();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        APPLOGGER.info("doGet() | ");

        doAction(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        APPLOGGER.info("doPost() | ");

        doAction(req, resp);

    }

    private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        if (electronicInboxFilter.getTotalRecords() == 0.00) {
            resetPages();
        }

        modelGeneratorTemplate.setModel("choiceName_",
                electronicInboxFilter.getName());

        modelGeneratorTemplate.setModel("choiceAddress_",
                electronicInboxFilter.getAddress());

        modelGeneratorTemplate.setModel("choicePlace_",
                electronicInboxFilter.getPlace());

        modelGeneratorTemplate.setModel("choicePage_",
                electronicInboxFilter.getPage());

        electronicinboxFilter.loadData();

        grayScale.loadGrayScaleFile();
        modelGeneratorTemplate.setModel("grayScale_",
                grayScale.getGrayScale());

        APPLOGGER.info("[getPage()       ] | " + electronicInboxFilter.getPage());

        if (Integer.parseInt(electronicInboxFilter.getPage()) > 1) {
            electronicInboxFilter.setPrevPage(Integer.parseInt(electronicInboxFilter.getPage()) - 1);

        } else {
            electronicInboxFilter.setPrevPage(Integer.parseInt(electronicInboxFilter.getPage()));

        }

        if (electronicInboxFilter.getTotalPages() == 0) {
            electronicInboxFilter.setPage("0");
            electronicInboxFilter.setPrevPage(0);
            electronicInboxFilter.setNextPage(0);
            modelGeneratorTemplate.setModel("choiceFirstPage_",
                    "0");
        } else {
            modelGeneratorTemplate.setModel("choiceFirstPage_",
                    "1");
        }

        modelGeneratorTemplate.setModel("choicePrevPage_",
                electronicInboxFilter.getPrevPage());
        APPLOGGER.info("[getPrevPage()   ] | " + electronicInboxFilter.getPrevPage());


        if (Integer.parseInt(electronicInboxFilter.getPage()) < electronicInboxFilter.getTotalPages()) {
            electronicInboxFilter.setNextPage(Integer.parseInt(electronicInboxFilter.getPage()) + 1);

        } else {
            electronicInboxFilter.setNextPage(electronicInboxFilter.getTotalPages());
        }

        modelGeneratorTemplate.setModel("choiceNextPage_",
                electronicInboxFilter.getNextPage());
        APPLOGGER.info("[getNextPage()   ] | " + electronicInboxFilter.getNextPage());


        modelGeneratorTemplate.setModel("choiceTotalPages_",
                electronicInboxFilter.getTotalPages());
        APPLOGGER.info("[getTotalPages()   ] | " + electronicInboxFilter.getTotalPages());


        modelGeneratorTemplate.setModel("database",
                electronicInboxDao.getList());

        try {

            electronicInboxFilter.setName("");
            electronicInboxFilter.setAddress("");
            electronicInboxFilter.setPlace("");
            electronicInboxFilter.setPage("1");

            Template template = templateProvider.getTemplate(getServletContext(), "electronicinbox");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());
            APPLOGGER.info("[\"electronicinbox\" loaded] |");

        } catch (TemplateException e) {
            e.printStackTrace();
            APPLOGGER.info("[\"electronicinbox\" NOT loaded] |");
        }

        LocalTime stopDoGet = now();

        APPLOGGER.info("[time of action (milliseconds)] | "
                + (ChronoUnit.NANOS.between(startDoGet, stopDoGet)) / 1000000);
    }

    private void resetFormFields() {

        electronicInboxDao.clearList();

        electronicInboxFilter.setName("");
        electronicInboxFilter.setAddress("");
        electronicInboxFilter.setPlace("");
        electronicInboxFilter.setPage("1");

        modelGeneratorTemplate.setModel("database", null);

        modelGeneratorTemplate.setModel("choiceName_", "");
        modelGeneratorTemplate.setModel("choiceAddress_", "");
        modelGeneratorTemplate.setModel("choicePlace_", "");
        modelGeneratorTemplate.setModel("choicePage_", "");

        modelGeneratorTemplate.setModel("choiceTotalPages_", "");
        modelGeneratorTemplate.setModel("choicePrevPage_", "");
        modelGeneratorTemplate.setModel("choiceNextPage_", "");
    }

    private void resetPages() {
        electronicInboxFilter.setPrevPage(1);
        electronicInboxFilter.setNextPage(1);
        electronicInboxFilter.setPage("1");
        electronicInboxFilter.setTotalPages(1);
    }
}
