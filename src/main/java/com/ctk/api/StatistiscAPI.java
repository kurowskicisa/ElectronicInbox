package com.ctk.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public class StatistiscAPI {


    @POST
    @Path("/statistics")
    public Response authenticateForm(
            @FormParam("user") String user,
            @FormParam("password") String password) {

        boolean authenticated = true;

        String web="<!doctype html>\n" +
                "<html lang=\"pl\">\n" +
                "\n" +
                "<head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta content-Type=\"text/html\"; charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "\n" +
                "    <meta http-equiv = \"refresh\" content =\"0, /statistics\">\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    windows.location.href = \"/statistics\"\n" +
                "    </script>\n" +
                "\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "    <title>Elektroniczne Skrzynki Podawcze</title>\n" +
                "\n" +
                "</head>" +
                "</html>";

        if (authenticated) {
            return Response.ok()
                    .entity(web)
                    .build();
        }

        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
