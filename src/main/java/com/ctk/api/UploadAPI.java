package com.ctk.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Path("/upload")
@Produces("text/html")
public class UploadAPI {
    private static Logger APPLOGGER = LogManager.getLogger(UploadAPI.class.getName());

    @POST
    @Path("/upload")
    public Response eibFirstPage() {

        APPLOGGER.info("POST | upload ");

        try (InputStream input = new URL("https://epuap.gov.pl/LESP/LESP.csv").openStream()) {

            System.out.println(input.toString().length());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.ok()
                .entity("")
                .build();
    }
}
