package com.ctk.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/eib_new")
public class electronicinboxLastParameterFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

        String page = Optional.ofNullable(req.getParameter("strona")).orElse("");

        if (!page.matches("[0-9]*")) {
            if (!resp.isCommitted()) {
                resp.sendRedirect("/electronicinbox");
            }
        }

        chain.doFilter(req, resp);

    }
}
