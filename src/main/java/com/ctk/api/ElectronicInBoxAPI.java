package com.ctk.api;

import com.ctk.model.ElectronicInboxFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static javax.ws.rs.core.Response.temporaryRedirect;

@Path("/eib")
@Produces("text/html")
public class ElectronicInBoxAPI {

    @Inject
    ElectronicInboxFilter electronicInboxFilter;

    private static final Logger APPLOGGER = LogManager.getLogger(ElectronicInBoxAPI.class.getName());

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

        electronicInboxFilter.setPage(
                String.valueOf((Integer.valueOf(firstPage)))
        );

        return temporaryRedirect(URI.create("/eib")).build();
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

        electronicInboxFilter.setPage(
                String.valueOf((Integer.valueOf(prevPage)))
        );

        return temporaryRedirect(URI.create("/eib")).build();
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

        electronicInboxFilter.setPage(
                String.valueOf((Integer.valueOf(nextPage)))
        );

        return temporaryRedirect(URI.create("/eib")).build();
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

        electronicInboxFilter.setPage(
                String.valueOf((Integer.valueOf(lastPage)))
        );

        return temporaryRedirect(URI.create("/eib")).build();
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

        return temporaryRedirect(URI.create("/eib")).build();
    }

    private void SetElextronicInBoxAPI(
            @FormParam("nazwa") String choiceName,
            @FormParam("adres") String choiceAddress,
            @FormParam("miejscowosc") String choicePlace,
            @FormParam("strona") String choicePage) {

        String _choiceName = null;
        String _choiceAddress = null;
        String _choicePlace = null;
        String _choicePage = null;

        _choiceName = Optional.ofNullable(new String(choiceName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8)).orElse("");
        _choiceAddress = Optional.ofNullable(new String(choiceAddress.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8)).orElse("");
        _choicePlace = Optional.ofNullable(new String(choicePlace.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8)).orElse("");
        _choicePage = Optional.ofNullable(new String(choicePage.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8)).orElse("");

        APPLOGGER.info("choiceName   : " + _choiceName);
        APPLOGGER.info("choiceAddress: " + _choiceAddress);
        APPLOGGER.info("choicePlace  : " + _choicePlace);
        APPLOGGER.info("choicePage   : " + _choicePage);

        electronicInboxFilter.setName(_choiceName);
        electronicInboxFilter.setAddress(_choiceAddress);
        electronicInboxFilter.setPlace(_choicePlace);
    }

}
