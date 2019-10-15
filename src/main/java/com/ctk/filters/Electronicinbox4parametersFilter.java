package com.ctk.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebFilter(urlPatterns = "/eib")
public class Electronicinbox4parametersFilter extends HttpFilter {

    private static Logger APPLOGGER = LogManager.getLogger(com.ctk.filters.Electronicinbox4parametersFilter.class.getName());

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        APPLOGGER.info("Electronicinbox4parametersFilter | doFilter");

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8 pageEncoding=\"UTF-8\"");

        int counterParams = 0;

        List<String> parametersName = new ArrayList<>();
//        parametersName.clear();
        Enumeration<String> en = req.getParameterNames();

        while (en.hasMoreElements()) {
            String paramName = en.nextElement();
            parametersName.add(String.valueOf(paramName));
        }

        if (parametersName.contains("strona")){
            counterParams++;
        }

        if (parametersName.contains("miejscowosc")){
            counterParams++;
        }

        if (parametersName.contains("adres")){
            counterParams++;
        }

        if (parametersName.contains("nazwa")){
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