package com.ctk.filters;

import com.ctk.dao.UserRepository;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class WebOKFilter extends HttpFilter {

    @Inject
    UserRepository userRepository;

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8\"");

        if (resp.getStatus() == 200) {
            chain.doFilter(req, resp);
        } else {
//            userRepository.getList().get(0).setAutenticate(false);
            resp.sendRedirect("");
        }
    }
}
