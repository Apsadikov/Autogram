package com.autogram.controller;

import com.autogram.model.entity.Comment;
import com.autogram.model.orm.repository.CommentRepository;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentServlet extends HttpServlet {
    private CommentRepository commentRepository;

    public CommentServlet() {
        commentRepository = new CommentRepository(DBConnection.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("user_id"));
        int postId = Integer.parseInt(req.getParameter("post_id"));
        String text = req.getParameter("text");

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setText(text);
        commentRepository.create(comment);

        resp.sendRedirect("http://localhost:8080/post?id=" + postId);
    }
}
