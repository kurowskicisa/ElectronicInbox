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
public class Web404Filter extends HttpFilter {

    @Inject
    UserRepository userRepository;

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        final Integer status;

        chain.doFilter(req, resp);

        status = resp.getStatus();

        if (status.equals(404)) {

            if (userRepository.getList().size() > 0) {
         //       userRepository.getList().get(0).setAutenticate(false);
            }

            resp.setHeader("Content-Type", "text/html; charset=UTF-8");
            resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8\"");

            if (!resp.isCommitted()) {
                resp.sendRedirect("");
            }
        }
    }
}
