package com.ctk.api;

import com.ctk.dao.UserDao;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.Response.temporaryRedirect;

@Path("/api")
@Produces("text/html")
public class StatisticAPI {

    @Inject
    UserDao userDao;

    @POST
    @Path("/statistics")
    public Response authenticateForm(
            @FormParam("user") String loguser,
            @FormParam("password") String password) {

        userDao.empty();
        userDao.loadUserFile();

        if (!userDao.getList().isEmpty()) {

            boolean authenticated = userDao.isAuthenticated(loguser, password);

            if (authenticated) {
                userDao.getList().get(0).setAuthenticate(true);

                return temporaryRedirect(URI.create("/statistics")).build();
            }
        }

        return temporaryRedirect(URI.create("/electronicinbox/")).build();
    }
}
