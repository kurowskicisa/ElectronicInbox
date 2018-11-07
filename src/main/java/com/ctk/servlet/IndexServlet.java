package com.ctk.servlet;

import com.ctk.dao.ElectronicInboxDao;
import com.ctk.dao.ElectronicinboxLoadFromFileFiltered;
import com.ctk.model.ElectronicInboxFilterFile;

import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

import static java.time.LocalTime.now;


@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {

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



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger appLogger = LoggerFactory.getLogger(IndexServlet.class);

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8");

        try {
            final String choiceName = req.getParameter("nazwa").trim();

            appLogger.info("[choiceName   ] " + choiceName);

            final String choiceAddress = req.getParameter("adres").trim();
            appLogger.info("[choiceAddress] " + choiceAddress);

            final String choicePlace = req.getParameter("miejscowosc").trim();
            appLogger.info("[choicePlace  ] " + choicePlace);

            final String choicePage = req.getParameter("strona").trim();
            appLogger.info("[choicePage   ] " + choicePage);


            electronicInboxFilterFile.setName(String.valueOf(choiceName));
            electronicInboxFilterFile.setAddress(String.valueOf(choiceAddress));
            electronicInboxFilterFile.setPlace(String.valueOf(choicePlace));

            if (Integer.parseInt(String.valueOf(choicePage)) == 0) {
                electronicInboxFilterFile.setPage("1");
            } else {
                electronicInboxFilterFile.setPage((String.valueOf(choicePage)));
            }

            electronicinboxLoadFromFileFiltered.loadData();

            modelGeneratorTemplate.setModel("choiceName_", choiceName);
            modelGeneratorTemplate.setModel("choiceAddress_", choiceAddress);
            modelGeneratorTemplate.setModel("choicePlace_", choicePlace);
            modelGeneratorTemplate.setModel("choicePage_", choicePage);


            modelGeneratorTemplate.setModel("choiceTotalPages_", electronicInboxFilterFile.getTotalPages());

            if (Integer.parseInt(electronicInboxFilterFile.getPage()) > 1) {
                modelGeneratorTemplate.setModel("choicePrevPage_", Integer.parseInt(electronicInboxFilterFile.getPage()) - 1);
            } else {
                modelGeneratorTemplate.setModel("choicePrevPage_", 1);
            }

            if (Integer.parseInt(electronicInboxFilterFile.getPage()) < electronicInboxFilterFile.getTotalPages()) {
                modelGeneratorTemplate.setModel("choiceNextPage_", Integer.parseInt(electronicInboxFilterFile.getPage()) + 1);
            } else {
                modelGeneratorTemplate.setModel("choiceNextPage_", electronicInboxFilterFile.getTotalPages());
            }

            appLogger.info("[counter: onPage] " + electronicInboxDao.getList().size());
            appLogger.info("[counter: Total filtered records] " + electronicInboxFilterFile.getTotalFilteredRecords());
            appLogger.info("[counter: Total records         ] " + electronicInboxFilterFile.getTotalRecords());

            modelGeneratorTemplate.setModel("database",
                    electronicInboxDao.getList());
        } catch (NullPointerException e) {
            appLogger.debug("[No data]");
            // appLogger.info("[No data]");
        }

        try {
            Template template = templateProvider.getTemplate(getServletContext(), "indexTemplate");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());

        } catch (TemplateException e) {
            e.printStackTrace();
        }

        LocalTime stopDoGet = now();
        appLogger.info("[time of action (milliseconds)] " + ((stopDoGet.getNano()-startDoGet.getNano())/1000000));
    }
}
