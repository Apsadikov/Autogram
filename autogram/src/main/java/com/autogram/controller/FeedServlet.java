package com.autogram.controller;

import com.autogram.model.entity.Post;
import com.autogram.model.orm.repository.PostRepository;
import com.autogram.model.orm.specification.post.PostByFriendSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FeedServlet extends HttpServlet {
    private PostRepository postRepository;

    @Override
    public void init() throws ServletException {
        postRepository = new PostRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<List<Post>> postList = postRepository
                .findAll(new PostByFriendSpecification((int) req.getSession().getAttribute("id")));

        req.setAttribute("title", "Feed");
        req.setAttribute("style", "feed");
        req.setAttribute("posts", postList.get());
        req.getRequestDispatcher("/feed.ftlh").forward(req, resp);
    }
}
