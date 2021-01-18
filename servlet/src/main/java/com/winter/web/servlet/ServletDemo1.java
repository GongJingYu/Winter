package com.winter.web.servlet;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletDemo1 implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Servlet Demo1 init");
        ServletContext servletContext = config.getServletContext();
//        servletContext.removeAttribute("uuid");
        System.out.println("servletContext.getInitParameter(\"driver-class-name\") = " + servletContext.getInitParameter("driver-class-name"));
        System.out.println("servletContext.getInitParameter(\"url\") = " + servletContext.getInitParameter("url"));
        System.out.println("servletContext.getInitParameter(\"username\") = " + servletContext.getInitParameter("username"));
        System.out.println("servletContext.getInitParameter(\"password\") = " + servletContext.getInitParameter("password"));

        System.out.println("servletContext.getRealPath(\"/\") = " + servletContext.getRealPath("/"));

        System.out.println("servletContext.getContextPath() = " + servletContext.getContextPath());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        System.out.println("Servlet Demo1 service");
        System.out.println("req.getAttribute(\"uuid\") = " + req.getAttribute("uuid"));

        ServletContext servletContext = req.getServletContext();
        System.out.println("servletContext.getAttribute(\"uuid\") = " + servletContext.getAttribute("uuid"));

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            out.println(name + " : " + value);
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet Demo1 destroy");
    }
}
