package com.ctk.servlet;

import com.ctk.dao.GrayScaleReadFile;
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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    GrayScale grayScale;

    @Inject
    GrayScaleReadFile grayScaleReadFile;

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.servlet.LoginServlet.class.getName());

    @Override
    public void init() {
        APPLOGGER.info("[WEB login | init()] | ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalTime startDoGet = now();

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

        grayScaleReadFile.loadGrayScaleFile();
        modelGeneratorTemplate.setModel("grayScale_",
                grayScale.getGrayScale());

        try {
            Template template = templateProvider.getTemplate(getServletContext(), "login");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());
            APPLOGGER.info("[WEB login | loaded] |");

        } catch (TemplateException e) {
            e.printStackTrace();
            APPLOGGER.info("[WEB login | NOT loaded] |");
        }

        LocalTime stopDoGet = now();

        APPLOGGER.info("[WEB login | time of action (milliseconds)] | "
                + (ChronoUnit.NANOS.between(startDoGet, stopDoGet)) / 1000000);
    }
}
