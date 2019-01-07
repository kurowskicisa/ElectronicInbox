package com.ctk.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebFilter(urlPatterns = "/eib")
public class electronicinbox4parametersFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8\"");

        Integer counterParams = 0;

        Enumeration en = req.getParameterNames();
        List<String> paramNames = Arrays.asList();
        paramNames.clear();

        while (en.hasMoreElements()) {
            en.nextElement();
            counterParams++;
        }

        if (counterParams != 4) {
            if (!resp.isCommitted()) {
                resp.sendRedirect("/electronicinbox/eib?nazwa=&adres=&miejscowosc=&strona=1");
            }
        }

        chain.doFilter(req, resp);

    }
}
