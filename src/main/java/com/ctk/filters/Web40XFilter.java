package com.ctk.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class Web40XFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        final Integer status;

        chain.doFilter(req, resp);

        status = resp.getStatus();

        if (status.equals(404) || (status.equals(405))) {
            resp.setHeader("Content-Type", "text/html; charset=UTF-8");
            resp.setContentType("text/html;charset=UTF-8; pageEncoding=\"UTF-8\"");

            if (!resp.isCommitted()) {
                resp.sendRedirect("/electronicinbox/");
            }
        }
    }
}
