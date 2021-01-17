package com.winter.web.servlet;

import com.winter.web.util.BaseServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/demo2")
public class ServletDemo2 extends BaseServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("config.getServletName() = " + config.getServletName());
        ServletContext context = config.getServletContext();
//        context.
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("Test BaseServlet OK ");
    }
}
