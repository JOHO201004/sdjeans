package com.sdjeans.sdjeans_app.C_app.Filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AllFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init!!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        HttpSession session = httpRequest.getSession();
        if (requestURI.contains("/login") || requestURI.contains("/regi")) {
            chain.doFilter(request, response);
        } else {
            if (session.getAttribute("memberId") != null) {
                System.out.println("IDが存在するため、Filterが実行されました");
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect("/login");
            }
        }

    }

    @Override
    public void destroy() {
        System.out.println("destroy!!");
    }

}