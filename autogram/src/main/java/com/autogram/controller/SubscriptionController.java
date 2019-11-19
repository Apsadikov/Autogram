package com.autogram.controller;

import com.autogram.model.entity.Subscriber;
import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.SubscriptionRepository;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.profile.ProfileUserIdSpecification;
import com.autogram.model.orm.specification.subscription.SubscriptionIsExistSpecification;
import com.autogram.model.orm.specification.subscription.SubscriptionUserSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionController extends HttpServlet {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;


    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
        subscriptionRepository = new SubscriptionRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            Optional<List<User>> user = userRepository
                    .findAll(new ProfileUserIdSpecification(Integer.valueOf(req.getParameter("id"))));
            Optional<List<User>> subscription = userRepository
                    .findAll(new SubscriptionUserSpecification(Integer.valueOf(req.getParameter("id"))));

            Optional<List<Subscriber>> subscriber = subscriptionRepository
                    .findAll(new SubscriptionIsExistSpecification(Integer.valueOf(req.getParameter("id")),
                            (int) req.getSession().getAttribute("id")));

            boolean isSubscribe = subscriber.isPresent() && subscriber.get().size() != 0;

            if (user.isPresent() && user.get().size() != 0) {
                if (subscription.isPresent() && subscription.get().size() != 0) {
                    req.setAttribute("subscriptions", subscription.get());
                } else {
                    req.setAttribute("subscriptions", new ArrayList<User>());
                }
                req.setAttribute("userProfile", user.get().get(0));
                req.setAttribute("title", "Subscription");
                req.setAttribute("isSubscribe", String.valueOf(isSubscribe));
                req.setAttribute("style", "subscription");
                req.setAttribute("owner", "false");
            } else {
                resp.sendRedirect("http://localhost:8080/profile");
                return;
            }
            req.getRequestDispatcher("/subscription.ftlh").forward(req, resp);
        } else {
            resp.sendRedirect("http://localhost:8080/profile");
            return;
        }
    }
}
