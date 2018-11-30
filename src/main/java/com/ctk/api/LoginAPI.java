package com.ctk.api;

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

    public LoginAPI(){
           }

    @GET
    @Path("/")

    public Response getLoginForm() {

        return Response.ok()
                .entity(loginWEB.LoginWEB())
                .build();
    }
}
