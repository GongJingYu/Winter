package com.winter.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/request1")
public class Request1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = UUID.randomUUID().toString();
        /**
         * i. getRequestURI() 获取请求的资源路径
         * ii. getRequestURL() 获取请求的统一资源定位符（绝对路径）
         * iii. getRemoteHost() 获取客户端的 ip 地址
         * iv. getHeader() 获取请求头
         * v. getParameter() 获取请求的参数
         * vi. getParameterValues() 获取请求的参数（多个值的时候使用）
         * vii. getMethod() 获取请求的方式 GET 或 POST
         * viii. setAttribute(key, value); 设置域数据
         * ix. getAttribute(key); 获取域数据
         * x. getRequestDispatcher() 获取请求转发对象
         */
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("========================================");
        System.out.println("Remote:" + req.getRemoteUser() + " " + req.getRemoteAddr() + " "
        + req.getRemoteHost() + " " + req.getRemotePort());
        System.out.println("req.getMethod() = " + req.getMethod());
        System.out.println("req.getParameter(\"age\") = " + req.getParameter("age"));

        req.setAttribute("name","宫静雨");
        req.setAttribute("age",21);

        req.setAttribute("uuid",s);
        req.getRequestDispatcher("/request2").forward(req,resp);

    }
}
