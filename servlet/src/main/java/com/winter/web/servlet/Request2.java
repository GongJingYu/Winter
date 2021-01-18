package com.winter.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/request2")
public class Request2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        System.out.println("req.getAttribute(\"name\") = " + req.getAttribute("name"));
        System.out.println("req.getAttribute(\"age\") = " + req.getAttribute("age"));

        Object uuid = req.getAttribute("uuid");

        PrintWriter out = resp.getWriter();
        out.println("<h1>"+uuid+"</h1>");

//        resp.sendRedirect("http://www.baidu.com");
    }
}
