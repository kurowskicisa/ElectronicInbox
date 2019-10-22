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
            @FormParam("firstPage") String firstPage) {

        APPLOGGER.info("POST | first ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace);

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
            @FormParam("prevPage") String prevPage) {

        APPLOGGER.info("POST | prev ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace);

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
            @FormParam("nextPage") String nextPage) {

        APPLOGGER.info("POST | next ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace);

        electronicInboxFilterFile.setPage(
                String.valueOf((Integer.valueOf(nextPage)))
        );

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    private ElectronicInBoxAPI getEncoder(String choiceName) {
        return this;
    }

    @POST
    @Path("/last")
    public Response eibLastPage(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("lastPage") String lastPage) {

        APPLOGGER.info("POST | last ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace);

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
            @FormParam("miejscowosc") String choicePlace) {

        APPLOGGER.info("GET | find ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace);

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    @POST
    @Path("/find")
    public Response eibPostFind(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace) {

        APPLOGGER.info("POST | find ");

        SetElextronicInBoxAPI(choiceName, choiceAddress, choicePlace);

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }

    private void SetElextronicInBoxAPI(@FormParam("nazwa") String choiceName, @FormParam("adres") String choiceAddress, @FormParam("miejscowosc") String choicePlace) {
        APPLOGGER.info("POST | find ");

        String _choiceName = null;
        String _choiceAddress = null;
        String _choicePlace = null;

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

        APPLOGGER.info("choiceName: " + _choiceName);
        APPLOGGER.info("choiceAddress: " + _choiceAddress);
        APPLOGGER.info("choicePlace: " + _choicePlace);

        electronicInboxFilterFile.setName(_choiceName);
        electronicInboxFilterFile.setAddress(_choiceAddress);
        electronicInboxFilterFile.setPlace(_choicePlace);
    }

}
