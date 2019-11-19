package com.autogram.controller;

import com.autogram.model.entity.Like;
import com.autogram.model.orm.repository.LikeRepository;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeServlet extends HttpServlet {
    private LikeRepository likeRepository;

    @Override
    public void init() throws ServletException {
        likeRepository = new LikeRepository(DBConnection.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Like like = new Like();
        like.setUserId((int) req.getSession().getAttribute("id"));
        like.setPostId(Integer.valueOf(req.getParameter("post_id")));
        if (req.getParameter("add").equals("true")) {
            likeRepository.create(like);
        } else {
            likeRepository.delete(like);
        }
    }
}
