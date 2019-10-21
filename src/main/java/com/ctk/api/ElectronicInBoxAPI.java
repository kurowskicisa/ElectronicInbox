package com.ctk.api;

import com.ctk.model.ElectronicInboxFilterFile;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/eib")
@Produces("text/html")
public class ElectronicInBoxAPI {

    @Inject
    ElectronicInboxFilterFile electronicInboxFilterFile;

    @Inject
    ElectronicInBoxRedirect electronicInBoxRedirect;

    @POST
    @Path("/first")
    public Response eibFirstPage(
            @FormParam("firstPage") String firstPage) {

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
            @FormParam("prevPage") String prevPage) {

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
            @FormParam("nextPage") String nextPage) {

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
            @FormParam("lastPage") String lastPage) {

        electronicInboxFilterFile.setPage(
                String.valueOf((Integer.valueOf(lastPage)))
        );

        return Response.ok()
                .entity(electronicInBoxRedirect.ElectronicInBoxRedirect())
                .build();
    }


}
