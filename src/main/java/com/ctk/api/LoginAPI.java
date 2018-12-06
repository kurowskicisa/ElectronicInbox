package com.ctk.api;

import com.ctk.dao.UserRepository;
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

    @Inject
    private UserRepository userRepository;

    public LoginAPI() {
    }

    @GET
    @Path("/")

    public Response getLoginForm() {

        if (userRepository.getList().size() > 0) {
            userRepository.getList().get(0).setAutenticate(false);
        }
        return Response.ok()
                .entity(loginWEB.LoginWEB())
                .build();
    }

    @POST
    @Path("/")
    public Response getLoginForm2() {

        return Response.ok()
                .entity(loginWEB.LoginWEB())
                .build();
    }
}
