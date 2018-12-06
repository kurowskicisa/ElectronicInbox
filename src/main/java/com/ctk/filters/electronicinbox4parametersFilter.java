package com.ctk.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(urlPatterns = "/electronicinbox")
public class electronicinbox4parametersFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8\"");

        Integer counterParams = 0;

        Enumeration en = req.getParameterNames();

        while (en.hasMoreElements()) {
            counterParams++;
            en.nextElement();
        }

        if (counterParams != 4) {
            if (!resp.isCommitted()) {
                resp.sendRedirect("");
            }
        }

        chain.doFilter(req, resp);

    }
}
