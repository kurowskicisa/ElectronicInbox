package com.ctk.filters;


import com.ctk.dao.UserRepository;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/statistics")
public class StatisticFilter extends HttpFilter {

    @Inject UserRepository userRepository;
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        boolean logged = false;
                logged = userRepository.getList().get(0).isAutenticate();

        System.out.println("Filter: logged: " + logged);

        if (logged) {
            System.out.println("Filter (if): logged");
            HttpServletResponse httpres = (HttpServletResponse) resp;
            httpres.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            httpres.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            httpres.setDateHeader("Expires", 0); // Proxies.
            httpres.flushBuffer();
            httpres.resetBuffer();

            chain.doFilter(req, resp);
            
        } else {
            System.out.println("Filter (if): NOT logged");
            resp.sendRedirect("");
         //   chain.doFilter(req, resp);
        }
        userRepository.getList().get(0).setAutenticate(false);
        logged = userRepository.getList().get(0).isAutenticate();


        System.out.println("Filter (end): logged: " + logged);
        System.out.println("End of filter");
    }
}
