package com.ctk.api;

import com.ctk.freemarker.ModelGeneratorTemplate;
import com.ctk.freemarker.TemplateAPI;
import com.ctk.freemarker.TemplateProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;

@Path("/api")
@Produces({"text/html"})
public class LoginAPI {

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.api.LoginAPI.class.getName());


    public LoginAPI(){
            }

    @Inject
    private TemplateAPI templateAPI;

    @Inject
    private ModelGeneratorTemplate modelGeneratorTemplate;

    @GET
    @Path("/login")
    public Response getLoginForm() {
        try {


            Template template = templateAPI.getTemplateAPI("login");

            StringWriter writer = new StringWriter();

            modelGeneratorTemplate.setModel("__",
                    "__");

            template.process(modelGeneratorTemplate.getModel(), writer);

            System.out.println(writer);

            return Response.ok(template).build();

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
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
