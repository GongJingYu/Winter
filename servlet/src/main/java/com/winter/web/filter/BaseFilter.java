package com.winter.web.filter;

import javax.servlet.*;
import javax.servlet.FilterChain;
import java.io.IOException;

public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
    }


    @Override
    public void destroy() {

    }
}
