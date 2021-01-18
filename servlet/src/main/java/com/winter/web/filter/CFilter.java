package com.winter.web.filter;

import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class CFilter implements Filter {

    private String charset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String t = filterConfig.getInitParameter("charset");
        if (t != null){
            charset = t;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);
        chain.doFilter(new MyHttpServletRequestWrapper((HttpServletRequest) request),response);
    }

    static class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{

        private HttpServletRequest request;

        public MyHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            if ("get".equalsIgnoreCase(request.getMethod())){
                String value = super.getParameter(name);
                if (value != null){
                    try {
                        value = new String(value.getBytes(StandardCharsets.ISO_8859_1),
                                request.getCharacterEncoding());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return value;
            }
            return super.getParameter(name);
        }
    }

    @Override
    public void destroy() {

    }
}
