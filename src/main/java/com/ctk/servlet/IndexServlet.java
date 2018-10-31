package com.ctk.servlet;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8");

        try {
            Template template = templateProvider.getTemplate(getServletContext(), "indexTemplate");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());

        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
