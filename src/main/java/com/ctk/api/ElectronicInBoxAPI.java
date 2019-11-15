package com.ctk.api;

import com.ctk.model.ElectronicInboxFilterFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Path("/eib")
@Produces("text/html")
public class ElectronicInBoxAPI {

    @Inject
    ElectronicInboxFilterFile electronicInboxFilterFile;

    @Inject
    ElectronicInBoxRedirect electronicInBoxRedirect;

    private static Logger APPLOGGER = LogManager.getLogger(ElectronicInBoxAPI.class.getName());

    @POST
    @Path("/first")
    public Response eibFirstPage(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage,
            @FormParam("firstPage") String firstPage) {

        APPLOGGER.info("POST | first ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace, choicePage);

        electronicInboxFilterFile.setPage(
                String.valueOf((Integer.valueOf(firstPage)))
        );

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    @POST
    @Path("/prev")
    public Response eibPrevPage(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage,
            @FormParam("prevPage") String prevPage) {

        APPLOGGER.info("POST | prev ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace, choicePage);

        electronicInboxFilterFile.setPage(
                String.valueOf((Integer.valueOf(prevPage)))
        );

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    @POST
    @Path("/next")
    public Response eibNexPage(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage,
            @FormParam("nextPage") String nextPage) {

        APPLOGGER.info("POST | next ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace, choicePage);

        electronicInboxFilterFile.setPage(
                String.valueOf((Integer.valueOf(nextPage)))
        );

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    @POST
    @Path("/last")
    public Response eibLastPage(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage,
            @FormParam("lastPage") String lastPage) {

        APPLOGGER.info("POST | last ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace, choicePage);

        electronicInboxFilterFile.setPage(
                String.valueOf((Integer.valueOf(lastPage)))
        );

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    @GET
    @Path("/find")
    public Response eibGetFind(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage) {

        APPLOGGER.info("GET | find ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace, choicePage);

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    @POST
    @Path("/find")
    public Response eibPostFind(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage) {

        APPLOGGER.info("POST | find ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace, choicePage);

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    private void SetElextronicInBoxAPI(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage) {

        APPLOGGER.info("POST | find ");

        String _choiceName = null;
        String _choiceAddress = null;
        String _choicePlace = null;
        String _choicePage = null;

        try {
            _choiceName = Optional.ofNullable(new String(choiceName.getBytes("iso-8859-1"), "utf-8")).orElse("");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            _choiceAddress = Optional.ofNullable(new String(choiceAddress.getBytes("iso-8859-1"), "utf-8")).orElse("");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            _choicePlace = Optional.ofNullable(new String(choicePlace.getBytes("iso-8859-1"), "utf-8")).orElse("");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            _choicePage = Optional.ofNullable(new String(choicePage.getBytes("iso-8859-1"), "utf-8")).orElse("");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        APPLOGGER.info("choiceName   : " + _choiceName);
        APPLOGGER.info("choiceAddress: " + _choiceAddress);
        APPLOGGER.info("choicePlace  : " + _choicePlace);
        APPLOGGER.info("choicePage   : " + _choicePage);

        electronicInboxFilterFile.setName(_choiceName);
        electronicInboxFilterFile.setAddress(_choiceAddress);
        electronicInboxFilterFile.setPlace(_choicePlace);
    }

}
