package com.autogram.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        Cookie cookieId = new Cookie("token", "");
        Cookie cookieToken = new Cookie("id", "");
        cookieId.setMaxAge(0);
        cookieToken.setMaxAge(0);
        resp.addCookie(cookieId);
        resp.addCookie(cookieToken);
        resp.sendRedirect("/login");
    }
}
