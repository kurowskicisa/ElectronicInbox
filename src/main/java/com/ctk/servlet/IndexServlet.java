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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/szukaj")
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

        System.out.println("[ doGet ]");
        ElectronicInbox cos = new ElectronicInbox("aaaa","bbbb","cccc", "dddd", "eeee", "ffff");

        electronicInboxDao.setList(cos);

        electronicinboxLoadFromFile.loadData();

        System.out.println("[ doGet ] [przed filtrem]: " + electronicInboxDao.getList().size());
        System.out.println("[ doGet ] [przed filtrem]: " + electronicInboxDao.getList().toString());


        try {
            final String choiceName = req.getParameter("nazwa").trim();
            final String choiceAddress = req.getParameter("adres").trim();
            final String choicePlace = req.getParameter("miejscowosc").trim();

            System.out.println("choiceName: [" + choiceName + "]");
            System.out.println("choiceAddress: [" + choiceAddress + "]");
            System.out.println("choicePlace: [" + choicePlace + "]");




            modelGeneratorTemplate.setModel("choiceName_", choiceName);
            modelGeneratorTemplate.setModel("choiceAddress_", choiceAddress);
            modelGeneratorTemplate.setModel("choicePlace_", choicePlace);

            modelGeneratorTemplate.setModel("database", electrinicInboxFilter.filterElectronicInbox(choiceName, choiceAddress, choicePlace));
            System.out.println("[ doGet ] [po filtrze]: " + electronicInboxDao.getList().size());
            System.out.println("[ doGet ] [po filtrze]: " + electronicInboxDao.getList().toString());

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[ doPost] ");

        final String choiceName = req.getParameter("nazwa").trim();
        final String choiceAddress = req.getParameter("adres").trim();
        final String choicePlace = req.getParameter("miejscowosc").trim();


        System.out.println("[ doPost] choiceName: [" + choiceName + "]");
        System.out.println("[ doPost] choiceAddress: [" + choiceAddress + "]");
        System.out.println("[ doPost] choicePlace: [" + choicePlace + "]");

        //    /electronicinbox/szukaj?nazwa=&adres=&miejscowosc=
    }
}
