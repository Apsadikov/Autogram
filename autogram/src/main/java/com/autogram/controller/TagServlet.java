package com.autogram.controller;

import com.autogram.model.entity.Post;
import com.autogram.model.orm.repository.PostRepository;
import com.autogram.model.orm.specification.post.PostByTagSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TagServlet extends HttpServlet {
    private PostRepository postRepository;

    @Override
    public void init() throws ServletException {
        postRepository = new PostRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<List<Post>> postList = postRepository
                .findAll(new PostByTagSpecification("#" + req.getParameter("search")));

        req.setAttribute("title", "Tag");
        req.setAttribute("style", "tag");
        req.setAttribute("posts", postList.get());
        req.getRequestDispatcher("/tag.ftlh").forward(req, resp);
    }
}
