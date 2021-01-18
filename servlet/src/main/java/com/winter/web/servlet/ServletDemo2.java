package com.winter.web.servlet;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class ServletDemo2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cookie").forward(req,resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("config.getServletName() = " + config.getServletName());
        String name = config.getInitParameter("name");
        String age = config.getInitParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        ServletContext servletContext = config.getServletContext();
        System.out.println("servletContext = " + servletContext);
        System.out.println("config = " + config);
        System.out.println("=====================================");
        String s = UUID.randomUUID().toString();
        servletContext.setAttribute("uuid",s);
        System.out.println("s = " + s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/app1").forward(req,resp);
    }
}
