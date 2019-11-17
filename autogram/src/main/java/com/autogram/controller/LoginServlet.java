package com.autogram.controller;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.user.UserEmailSpecification;
import com.autogram.util.DBConnection;
import com.autogram.util.PasswordEncrypt;
import com.autogram.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", req.getAttribute("errors"));
        req.setAttribute("email", req.getAttribute("email"));
        req.setAttribute("password", req.getAttribute("password"));
        req.setAttribute("remember_me", req.getParameter("remember_me") != null);

        setTemplate(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = new Validator();

        String password = validator.value("password", req.getParameter("password"))
                .isRequired().onError("Введите пароль")
                .validate();
        String email = validator.value("email", req.getParameter("email"))
                .isRequired().onError("Введите Email")
                .validate();

        if (validator.getErrorList().size() != 0) {
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("errors", validator.getErrorList());
            req.setAttribute("remember_me", req.getParameter("remember_me") != null);
            setTemplate(req, resp);
        } else {
            Optional<List<User>> user = userRepository.query(new UserEmailSpecification(email));
            if (user.isPresent() && user.get().size() != 0) {
                if (PasswordEncrypt.checkPassword(password, user.get().get(0).getHashPassword())) {
                    req.getSession().setMaxInactiveInterval(-1);
                    req.getSession().setAttribute("id", user.get().get(0).getId());
                    req.getSession().setAttribute("token", user.get().get(0).getToken());
                    if (req.getParameter("remember_me") != null) {
                        Cookie cookieToken = new Cookie("token", user.get().get(0).getToken());
                        Cookie cookieId = new Cookie("id", String.valueOf(user.get().get(0).getId()));
                        cookieToken.setMaxAge(2592000);
                        cookieId.setMaxAge(2592000);
                        resp.addCookie(cookieId);
                        resp.addCookie(cookieToken);
                        resp.sendRedirect("/profile");
                    }
                } else {
                    validator.addError("password", "Неверный пароль");
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    req.setAttribute("errors", validator.getErrorList());
                    req.setAttribute("remember_me", req.getParameter("remember_me") != null);
                    setTemplate(req, resp);
                }
            } else {
                validator.addError("email", "Такой email не используется");
                req.setAttribute("email", email);
                req.setAttribute("password", password);
                req.setAttribute("errors", validator.getErrorList());
                req.setAttribute("remember_me", req.getParameter("remember_me") != null);
                setTemplate(req, resp);
            }
        }
    }

    private void setTemplate(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("title", "Login");
        req.setAttribute("style", "login");
        req.setAttribute("js", "login");
        try {
            req.getRequestDispatcher("/login.ftlh").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
