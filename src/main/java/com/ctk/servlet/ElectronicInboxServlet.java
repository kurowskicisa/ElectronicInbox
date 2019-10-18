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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        APPLOGGER.info("doGet() | ");

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

        resetWebDate();

        try {
            final String choiceName = req.getParameter("nazwa").trim();

            APPLOGGER.info("[choiceName   ] | " + choiceName);

            final String choiceAddress = req.getParameter("adres").trim();
            APPLOGGER.info("[choiceAddress] | " + choiceAddress);

            final String choicePlace = req.getParameter("miejscowosc").trim();
            APPLOGGER.info("[choicePlace  ] | " + choicePlace);

            final String choicePage = req.getParameter("strona").trim();
            APPLOGGER.info("[choicePage   ] | " + choicePage);

            electronicInboxFilterFile.setName(choiceName);
            electronicInboxFilterFile.setAddress(choiceAddress);
            electronicInboxFilterFile.setPlace(choicePlace);

            if (choicePage == null) {
                electronicInboxFilterFile.setPage("1");
                APPLOGGER.info("[getPage()   ] | null ");

            } else {
                electronicInboxFilterFile.setPage(choicePage);
                APPLOGGER.info("[getPage()   ] | NOT null ");

            }

            electronicinboxLoadFromFileFiltered.loadData();

            grayScaleReadFile.loadGrayScaleFile();
            modelGeneratorTemplate.setModel("grayScale_",
                    grayScale.getGrayScale());

            modelGeneratorTemplate.setModel("choiceName_",
                    electronicInboxFilterFile.getName());

            modelGeneratorTemplate.setModel("choiceAddress_",
                    electronicInboxFilterFile.getAddress());

            modelGeneratorTemplate.setModel("choicePlace_",
                    electronicInboxFilterFile.getPlace());

            modelGeneratorTemplate.setModel("choicePage_",
                    electronicInboxFilterFile.getPage());

            modelGeneratorTemplate.setModel("choiceTotalPages_",
                    electronicInboxFilterFile.getTotalPages());

            if (electronicInboxFilterFile.getPage().isEmpty()) {
                electronicInboxFilterFile.setPage("1");
            }

            if (!electronicInboxFilterFile.getPage().matches("[0-9]*")) {
                electronicInboxFilterFile.setPage("1");
            }

            APPLOGGER.info("[getPage()   ] | " + electronicInboxFilterFile.getPage());

            if (Integer.parseInt(electronicInboxFilterFile.getPage()) > 1) {
                electronicInboxFilterFile.setPrevPage(Integer.parseInt(electronicInboxFilterFile.getPage()) - 1);

            } else {
                electronicInboxFilterFile.setPrevPage(Integer.parseInt(electronicInboxFilterFile.getPage()));

            }

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


            APPLOGGER.info("[counter: onPage] | " + electronicInboxDao.getList().size());
            APPLOGGER.info("[counter: Total filtered records] | "
                    + electronicInboxFilterFile.getTotalFilteredRecords());
            APPLOGGER.info("[counter: Total records         ] | "
                    + electronicInboxFilterFile.getTotalRecords());

            modelGeneratorTemplate.setModel("database",
                    electronicInboxDao.getList());

        } catch (NullPointerException e) {
            APPLOGGER.info("[No web parameters] | ");
        }

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

    private void resetWebDate() {

        electronicInboxDao.clearList();

        electronicInboxFilterFile.setName("");
        electronicInboxFilterFile.setAddress("");
        electronicInboxFilterFile.setPlace("");
        electronicInboxFilterFile.setPage("");

        modelGeneratorTemplate.setModel("database", null);

        modelGeneratorTemplate.setModel("choiceName_", "");
        modelGeneratorTemplate.setModel("choiceAddress_", "");
        modelGeneratorTemplate.setModel("choicePlace_", "");
        modelGeneratorTemplate.setModel("choicePage_", "");

        modelGeneratorTemplate.setModel("choiceTotalPages_", "");
        modelGeneratorTemplate.setModel("choicePrevPage_", "");
        modelGeneratorTemplate.setModel("choiceNextPage_", "");
    }
}
