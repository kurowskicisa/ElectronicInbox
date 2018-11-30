package com.ctk.servlet;

import com.ctk.dao.UserReadFile;
import com.ctk.dao.UserRepository;
import com.ctk.model.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/statistics2222")
public class StatisticServlet extends HttpServlet {

    private UserRepository userRepository;

    @Inject
    private UserReadFile userReadFile;

    @Inject
    public StatisticServlet(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    protected void doGet (HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        System.out.println("DOGET | StatisticServlet");
        req.getSession(true)
                .setAttribute("logged", true);

        Object logged = req.getSession().getAttribute("logged");

        System.out.println("DOGET | StatisticServlet | logged: " + logged);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DOPOST | StatisticServlet");
    }
}
