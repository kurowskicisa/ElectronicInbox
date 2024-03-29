package com.ctk.filters;

import com.ctk.dao.UserDao;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/statistics"})
public class StatisticFilter extends HttpFilter {

    @Inject
    UserDao userDao;

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        boolean logged;

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

        if (userDao.getList().size() > 0) {

            logged = userDao.getList().get(0).isAuthenticate();

            if (logged) {
                resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                resp.setDateHeader("Expires", 0); // Proxies.
                resp.flushBuffer();
                resp.resetBuffer();
                resp.setHeader("Content-Type", "text/html; charset=UTF-8");
                resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");
                req.setCharacterEncoding("UTF-8");

                chain.doFilter(req, resp);
            }
        }
        if (!resp.isCommitted()) {
            resp.sendRedirect("/electronicinbox/");
            chain.doFilter(req, resp);
        }
    }
}
