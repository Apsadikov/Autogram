package com.autogram.controller;

import com.autogram.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Login");
        req.setAttribute("style", "login");
        req.setAttribute("js", "login");
        req.setAttribute("errors", req.getAttribute("errors"));


        req.getRequestDispatcher("/login.ftlh").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = new Validator();

        String password = validator.value("password", req.getParameter("password"))
                .isRequired().onError("Введите пароль")
                .isPassword().onError("Пароль должен быть не меньше 8 символов и не больше 32. " +
                        "Должен содержать только цифры и буквы латинского алфавита")
                .validate();
        String email = validator.value("email", req.getParameter("email"))
                .isRequired().onError("Введите Email")
                .isEmail().onError("Некоректный Email")
                .validate();

        req.setAttribute("title", "login");
        req.setAttribute("style", "login");
        req.setAttribute("js", "login");
        req.setAttribute("errors", validator.getErrorList());
        req.getRequestDispatcher("/login.ftlh").forward(req, resp);
    }
}
