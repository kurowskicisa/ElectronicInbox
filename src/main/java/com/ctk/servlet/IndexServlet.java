package com.ctk.servlet;

import com.ctk.dao.ElectrinicInboxFilter;
import com.ctk.dao.ElectronicInboxDao;
import com.ctk.dao.ElectronicinboxLoadFromFile;
import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import com.ctk.model.ElectronicInbox;
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
    private ElectronicinboxLoadFromFile electronicinboxLoadFromFile;

    @Inject
    private ElectronicInboxDao electronicInboxDao;

    @Inject
    private ElectronicInbox electronicInbox;

    @Inject
    private ElectrinicInboxFilter electrinicInboxFilter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8");



        electronicinboxLoadFromFile.loadData(1);

        try {
            final String choiceName = req.getParameter("nazwa").trim();
            final String choiceAddress = req.getParameter("adres").trim();
            final String choicePlace = req.getParameter("miejscowosc").trim();


            modelGeneratorTemplate.setModel("choiceName_", choiceName);
            modelGeneratorTemplate.setModel("choiceAddress_", choiceAddress);
            modelGeneratorTemplate.setModel("choicePlace_", choicePlace);

            modelGeneratorTemplate.setModel("database",
                    electronicInboxDao.getList());
             //       electrinicInboxFilter.filterElectronicInbox(choiceName, choiceAddress, choicePlace));

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
