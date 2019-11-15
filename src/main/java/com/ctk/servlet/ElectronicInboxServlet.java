package com.ctk.servlet;

import com.ctk.dao.ElectronicInboxDao;
import com.ctk.dao.ElectronicinboxLoadFromFileFiltered;

import com.ctk.dao.GrayScaleReadFile;
import com.ctk.model.ElectronicInboxFilterFile;

import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;

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
@WebServlet(urlPatterns = "/eib")
public class ElectronicInboxServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    private ElectronicinboxLoadFromFileFiltered electronicinboxLoadFromFileFiltered;

    @Inject
    private ElectronicInboxDao electronicInboxDao;

    @Inject
    private ElectronicInboxFilterFile electronicInboxFilterFile;

    @Inject
    private GrayScale grayScale;

    @Inject
    private GrayScaleReadFile grayScaleReadFile;

    private static Logger APPLOGGER = LogManager.getLogger(ElectronicInboxServlet.class.getName());

    @Override
    public void init() {

        APPLOGGER.info("init() | ");

        resetFormFields();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APPLOGGER.info("doGet() | ");

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        if (electronicInboxFilterFile.getTotalRecords() == 0.00) {
            resetPages();
        }

        modelGeneratorTemplate.setModel("choiceName_",
                electronicInboxFilterFile.getName());

        modelGeneratorTemplate.setModel("choiceAddress_",
                electronicInboxFilterFile.getAddress());

        modelGeneratorTemplate.setModel("choicePlace_",
                electronicInboxFilterFile.getPlace());

        modelGeneratorTemplate.setModel("choicePage_",
                electronicInboxFilterFile.getPage());

        electronicinboxLoadFromFileFiltered.loadData();

        grayScaleReadFile.loadGrayScaleFile();
        modelGeneratorTemplate.setModel("grayScale_",
                grayScale.getGrayScale());

        APPLOGGER.info("[getPage()       ] | " + electronicInboxFilterFile.getPage());

        if (Integer.parseInt(electronicInboxFilterFile.getPage()) > 1) {
            electronicInboxFilterFile.setPrevPage(Integer.parseInt(electronicInboxFilterFile.getPage()) - 1);

        } else {
            electronicInboxFilterFile.setPrevPage(Integer.parseInt(electronicInboxFilterFile.getPage()));

        }

        if (electronicInboxFilterFile.getTotalPages() == 0){
            electronicInboxFilterFile.setPage("0");
            electronicInboxFilterFile.setPrevPage(0);
        }

        modelGeneratorTemplate.setModel("choiceFirstPage_",
                electronicInboxFilterFile.getPage());

        modelGeneratorTemplate.setModel("choicePrevPage_",
                electronicInboxFilterFile.getPrevPage());
        APPLOGGER.info("[getPrevPage()   ] | " + electronicInboxFilterFile.getPrevPage());


        if (Integer.parseInt(electronicInboxFilterFile.getPage()) < electronicInboxFilterFile.getTotalPages()) {
            electronicInboxFilterFile.setNextPage(Integer.parseInt(electronicInboxFilterFile.getPage()) + 1);

        } else {
            electronicInboxFilterFile.setNextPage(electronicInboxFilterFile.getTotalPages());
        }

        modelGeneratorTemplate.setModel("choiceNextPage_",
                electronicInboxFilterFile.getNextPage());
        APPLOGGER.info("[getNextPage()   ] | " + electronicInboxFilterFile.getNextPage());


        modelGeneratorTemplate.setModel("choiceTotalPages_",
                electronicInboxFilterFile.getTotalPages());
        APPLOGGER.info("[getTotalPages()   ] | " + electronicInboxFilterFile.getTotalPages());


        modelGeneratorTemplate.setModel("database",
                electronicInboxDao.getList());

        try {

            electronicInboxFilterFile.setName("");
            electronicInboxFilterFile.setAddress("");
            electronicInboxFilterFile.setPlace("");
            electronicInboxFilterFile.setPage("1");

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        APPLOGGER.info("doPost() | ");
        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {

            electronicInboxFilterFile.setName("");
            electronicInboxFilterFile.setAddress("");
            electronicInboxFilterFile.setPlace("");
            electronicInboxFilterFile.setPage("1");

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

        electronicInboxFilterFile.setName("");
        electronicInboxFilterFile.setAddress("");
        electronicInboxFilterFile.setPlace("");
        electronicInboxFilterFile.setPage("1");

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

        electronicInboxFilterFile.setPrevPage(1);
        electronicInboxFilterFile.setNextPage(1);
        electronicInboxFilterFile.setPage("1");
        electronicInboxFilterFile.setTotalPages(1);

    }
}
