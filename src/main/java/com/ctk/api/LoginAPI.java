package com.ctk.api;

import com.ctk.dao.UserRepository;
import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateProvider;
import freemarker.template.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
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

    @GET
    @Path("/")

    public Response getLoginForm() {

        Template template = null;

        if (userRepository.getList().size() > 0) {
            userRepository.getList().get(0).setAutenticate(false);
        }

//        try {
//            template = templateProvider.getTemplate1("login");
//            modelGeneratorTemplate.setModel("err_",
//                    "1");
//            StringWriter stringWriter = new StringWriter();
//
//            try {
//                template.process(modelGeneratorTemplate.getModel(), stringWriter);
//            } catch (TemplateException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
//        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");
//
//        try {
//            template = templateProvider.getTemplate(getServletContext(), "login");
//
//            template.process(modelGeneratorTemplate.getModel(), resp.getWriter());
//            APPLOGGER.info("[WEB login | loaded] |");
//
//        } catch (TemplateException e) {
//            e.printStackTrace();
//  //          APPLOGGER.info("[WEB login | NOT loaded] |");
//        }


        return Response.ok()
//                .entity(template.toString())
                .entity(loginWEB.LoginWEB())
                .build();
    }

}
