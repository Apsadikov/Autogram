package com.autogram.controller;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.user.UserByNameSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<List<User>> userList = userRepository.findAll(new UserByNameSpecification(req.getParameter("search")));

        if (userList.isPresent()) {
            req.setAttribute("users", userList.get());
            req.getRequestDispatcher("/users.ftlh").forward(req, resp);
        } else {
            resp.getWriter().println("");
        }
    }
}
