package com.autogram.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("____________________________");
        System.out.println("Token: " + req.getSession().getAttribute("token"));
        Arrays.stream(req.getCookies()).forEach(cookie -> System.out.println(cookie.getName() + " : " +cookie.getValue()));
        System.out.println("____________________________");
        req.setAttribute("title", "Profile");
        req.setAttribute("style", "profile");
        req.setAttribute("js", "profile");
        req.getRequestDispatcher("/profile.ftlh").forward(req, resp);

    }
}