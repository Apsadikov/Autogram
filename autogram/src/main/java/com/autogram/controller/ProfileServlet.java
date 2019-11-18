package com.autogram.controller;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.user.ProfileUserIdSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProfileServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int userId = -1;
        if (id == null || Integer.valueOf(id) == (int) req.getSession().getAttribute("id")) {
            userId = (int) req.getSession().getAttribute("id");
        }
        Optional<List<User>> user = userRepository
                .query(new ProfileUserIdSpecification(userId == -1 ? Integer.valueOf(id) : userId));

        if (user.isPresent() && user.get().size() != 0) {
            req.setAttribute("userProfile", user.get().get(0));
            req.setAttribute("title", "Profile");
            req.setAttribute("style", "profile");
            req.setAttribute("js", "profile");
            req.setAttribute("owner", String.valueOf(userId != -1));
            req.getRequestDispatcher("/profile.ftlh").forward(req, resp);
        } else {
            //TODO redirect
        }
    }
}
