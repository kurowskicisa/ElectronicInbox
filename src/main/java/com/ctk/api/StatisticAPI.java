package com.ctk.api;

import com.ctk.dao.UserReadFile;
import com.ctk.dao.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
@Produces("text/html")
public class StatisticAPI {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserReadFile userReadFile;

    @Inject
    StatisticWEB statisticWEB;

    @POST
    @Path("/statistics")
    public Response authenticateForm(
            @FormParam("user") String user,
            @FormParam("password") String password) {

        userRepository.empty();
        userReadFile.loadUserFile();
        boolean authenticated = userRepository.isAutenticated(user, password);

        if (authenticated) {
            userRepository.getList().get(0).setAutenticate(true);
        } else {
            userRepository.getList().get(0).setAutenticate(false);
        }

        return Response.ok()
                .entity(statisticWEB.StatisticWeb())
                .build();
    }
}
