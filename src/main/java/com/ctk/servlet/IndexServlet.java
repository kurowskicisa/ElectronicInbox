package com.ctk.servlet;

import com.ctk.dao.ElectronicInboxDao;
import com.ctk.dao.ElectronicinboxLoadFromFileFiltered;
import com.ctk.model.ElectronicInboxFilterFile;

import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

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

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8");

        try {
            final String choiceName = req.getParameter("nazwa").trim();
            final String choiceAddress = req.getParameter("adres").trim();
            final String choicePlace = req.getParameter("miejscowosc").trim();
            final String choicePage = req.getParameter("strona").trim();

            electronicInboxFilterFile.setName(choiceName);
            electronicInboxFilterFile.setAddress(choiceAddress);
            electronicInboxFilterFile.setPlace(choicePlace);

            if (Integer.parseInt(choicePage) == 0) {
                electronicInboxFilterFile.setPage("1");
            } else {
                electronicInboxFilterFile.setPage(choicePage);
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

            modelGeneratorTemplate.setModel("database",
                    electronicInboxDao.getList());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        try {
            Template template = templateProvider.getTemplate(getServletContext(), "indexTemplate");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());

        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
