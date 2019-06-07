package com.ctk.api;

import com.ctk.dao.GrayScaleReadFile;
import com.ctk.dao.UserRepository;
import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import com.ctk.model.GrayScale;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/")
@Produces("text/html")
public class LoginAPI {

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.api.LoginAPI.class.getName());

    @Inject
    private LoginWEB loginWEB;

    @Inject
    private UserRepository userRepository;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @Inject
    GrayScale grayScale;

    @Inject
    GrayScaleReadFile grayScaleReadFile;

    @GET
    @Path("/")

    public Response getLoginApi() {

        if (userRepository.getList().size() > 0) {
            userRepository.getList().get(0).setAutenticate(false);
        }

      //  Template test234 = loadLoginAPI();



        return Response.ok()
//                .entity(test234.toString())
                .entity(loginWEB.LoginWEB())
                .build();
    }

    private Template loadLoginAPI(){

        Template template = null;

        grayScaleReadFile.loadGrayScaleFile();
        modelGeneratorTemplate.setModel("grayScale_",
                grayScale.getGrayScale());

        modelGeneratorTemplate.setModel("err_",
                "1");

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            cfg.setDirectoryForTemplateLoading(new File("C:\\wildfly\\standalone"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        Template cfgTemplate =null;
        try {
            cfgTemplate = cfg.getTemplate("test 003");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map root = new HashMap();
        root.put("grayScale_",  grayScale.getGrayScale() );
        root.put("err_",  "1" );

        Template temp = null;

        try {
            temp = cfg.getTemplate("login.ftlh");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Merge data-model with template */
        Writer out = new OutputStreamWriter(System.out);
        try {
            temp.process(root, out);

        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

        try {
            template = templateProvider.getTemplate(getServletContext(), "login");

            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());

        } catch (TemplateException e) {
            e.printStackTrace();
            APPLOGGER.info("[WEB login | NOT loaded] |");
        }
        */

        return cfgTemplate;
    }

}
