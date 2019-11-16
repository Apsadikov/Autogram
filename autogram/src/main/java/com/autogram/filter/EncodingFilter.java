package com.autogram.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!((HttpServletRequest) servletRequest).getRequestURI().contains("/static/")) {
            servletRequest.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("charset=UTF-8");
            servletResponse.setCharacterEncoding("UTF-8");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
