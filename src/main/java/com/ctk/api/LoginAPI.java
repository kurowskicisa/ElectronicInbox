package com.ctk.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/eib")
public class LoginAPI {

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.api.LoginAPI.class.getName());


    public LoginAPI(){
            }

    @GET
    @Path("/login")
    public Response getLoginForm() {
        String form = "<form method=\"post\" action=\"authenticate\">\n"
                + "  Login: <input type=\"text\" name=\"login\"/><br/>\n"
                + "  Password: <input type=\"password\" name=\"password\"/><br/>\n"
                + "  <input type=\"submit\" value=\"Login\"/>\n"
                + "</form>";

        return Response.ok(form).build();
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
