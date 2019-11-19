package com.autogram.controller;

import com.autogram.model.entity.Post;
import com.autogram.model.entity.Subscriber;
import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.PostRepository;
import com.autogram.model.orm.repository.SubscriptionRepository;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.post.PostUserIdSpecification;
import com.autogram.model.orm.specification.profile.ProfileUserIdSpecification;
import com.autogram.model.orm.specification.subscription.SubscriptionIsExistSpecification;
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
    private PostRepository postRepository;
    private SubscriptionRepository subscriptionRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
        postRepository = new PostRepository(DBConnection.getInstance());
        subscriptionRepository = new SubscriptionRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int userId = -1;
        if (id == null || Integer.valueOf(id) == (int) req.getSession().getAttribute("id")) {
            userId = (int) req.getSession().getAttribute("id");
        }
        Optional<List<User>> user = userRepository
                .findAll(new ProfileUserIdSpecification(userId == -1 ? Integer.valueOf(id) : userId));
        Optional<List<Subscriber>> subscriber = subscriptionRepository
                .findAll(new SubscriptionIsExistSpecification(userId == -1 ? Integer.valueOf(id) : userId, (int) req.getSession().getAttribute("id")));

        boolean isSubscribe = subscriber.isPresent() && subscriber.get().size() != 0;

        if (user.isPresent() && user.get().size() != 0) {
            Optional<List<Post>> postList = postRepository
                    .findAll(new PostUserIdSpecification(userId == -1 ? Integer.valueOf(id) : userId));

            req.setAttribute("userProfile", user.get().get(0));
            req.setAttribute("posts", postList.get());
            req.setAttribute("title", "Profile");
            req.setAttribute("style", "profile");
            req.setAttribute("js", "profile");
            req.setAttribute("isSubscribe", String.valueOf(isSubscribe));
            req.setAttribute("owner", String.valueOf(userId != -1));
            req.getRequestDispatcher("/profile.ftlh").forward(req, resp);
        } else {
            resp.sendRedirect("http://localhost:8080/profile");
        }
    }
}
