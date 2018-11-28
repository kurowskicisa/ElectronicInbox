package com.ctk.api;

import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateAPI;
import com.ctk.freemarker.TemplateProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;

@Path("/api")
@Produces({"text/html"})
public class LoginAPI {
    @Context
    ServletContext servletContext;
    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.api.LoginAPI.class.getName());

    private static final String TEMPLATE_DIRECTORY_PATH = "WEB-INF/fm-templates";
    private static final String TEMPLATE_EXTENSION = ".ftlh";

    public LoginAPI(){
            }

    @Inject
    private TemplateAPI templateAPI;
    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @GET
    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLoginForm() {

   //     java.nio.file.Path pathLogin = Paths.get(System.getProperty("javax.servlet.context.orderedLibs"), "login.fthl");



        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setServletContextForTemplateLoading(servletContext, TEMPLATE_DIRECTORY_PATH);

        Template pathLogin2 = null;

        try {
            pathLogin2 = cfg.getTemplate("login" + TEMPLATE_EXTENSION);
            System.out.println(pathLogin2);           
        } catch (IOException e) {
            e.printStackTrace();
        }

        Template template = null;

        System.out.println(pathLogin2);

        return Response.ok()
                .entity(pathLogin2)
                .build();
    }

    @POST
    @Path("/authenticate")
    public Response authenticateForm(
            @FormParam("user") String user,
            @FormParam("password") String password) {

        // nazwaPola=wartoscPola&nazwaPola2=wartoscPola2
        // login=uzytkownik&password=haslo123

        APPLOGGER.info("User: " + user);
        System.out.println("User: " + user);

        APPLOGGER.info("Password: " + password);
        System.out.println("Password: " + password);

        boolean authenticated = true;

//        boolean authenticated = userStore.getBase()
//                .entrySet()
//                .stream()
//                .map(Map.Entry::getValue)
//                //.map(e -> e.getValue())
//                //.map(u -> u.getCredentials())
//                .map(User::getCredentials)
//                .anyMatch(c -> c.getUser().equals(login)
//                        && c.getPassword().equals(password));

        if (authenticated) {
            return Response.ok()
                    .entity("user "+ user)
                    .build();
        }

        return Response.status(Response.Status.OK).build();
    }
}
