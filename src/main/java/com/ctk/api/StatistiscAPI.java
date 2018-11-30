package com.ctk.api;

import com.ctk.dao.UserReadFile;
import com.ctk.dao.UserRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public class StatistiscAPI {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserReadFile userReadFile;

    @Inject
    StatisticWeb statisticWeb;

    @POST
    @Path("/statistics")
    public Response authenticateForm(
            @FormParam("user") String user,
            @FormParam("password") String password) {

        System.out.println("Statistics | API POST");

        userReadFile.loadUserFile();
        boolean authenticated = userRepository.isAutenticated(user, password);
    //    userRepository.clearList();

        if (authenticated) {

            userRepository.getList().get(0).setAutenticate(true);

            return Response.ok()
                    .entity(statisticWeb.getWeb())
                    .build();
        }

        return Response.ok()
                .entity(statisticWeb.getWebstart())
                .build();
    }
}
