package com.autogram.controller;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.user.ProfileUserIdSpecification;
import com.autogram.model.orm.specification.user.SubscriberUserSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriberController extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            Optional<List<User>> user = userRepository
                    .query(new ProfileUserIdSpecification(Integer.valueOf(req.getParameter("id"))));
            Optional<List<User>> subscription = userRepository
                    .query(new SubscriberUserSpecification(Integer.valueOf(req.getParameter("id"))));

            if (user.isPresent() && user.get().size() != 0) {
                if (subscription.isPresent() && subscription.get().size() != 0) {
                    req.setAttribute("subscriptions", subscription.get());
                } else {
                    req.setAttribute("subscriptions", new ArrayList<User>());
                }
                req.setAttribute("userProfile", user.get().get(0));
                req.setAttribute("title", "Subscriber");
                req.setAttribute("style", "subscription");
                req.setAttribute("owner", req.getParameter("id"));
            } else {
                //TODO redirect
            }
            req.getRequestDispatcher("/subscription.ftlh").forward(req, resp);
        } else {
            //TODO redirect
        }
    }
}
