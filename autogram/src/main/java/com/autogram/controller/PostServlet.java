package com.autogram.controller;

import com.autogram.model.entity.*;
import com.autogram.model.orm.repository.*;
import com.autogram.model.orm.specification.comment.CommentByPostIdSpecification;
import com.autogram.model.orm.specification.image.ImageByPostIdSpecification;
import com.autogram.model.orm.specification.like.LikeByUserIdSpecification;
import com.autogram.model.orm.specification.post.PostByIdSpecification;
import com.autogram.model.orm.specification.profile.ProfileUserIdSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PostServlet extends HttpServlet {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private ImageRepository imageRepository;
    private LikeRepository likeRepository;

    @Override
    public void init() throws ServletException {
        postRepository = new PostRepository(DBConnection.getInstance());
        userRepository = new UserRepository(DBConnection.getInstance());
        commentRepository = new CommentRepository(DBConnection.getInstance());
        imageRepository = new ImageRepository(DBConnection.getInstance());
        likeRepository = new LikeRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Optional<Post> post = postRepository.findOne(new PostByIdSpecification(id));
        Optional<User> user = userRepository.findOne(new ProfileUserIdSpecification(post.get().getUserId()));
        Optional<List<Comment>> commentList = commentRepository.findAll(new CommentByPostIdSpecification(post.get().getId()));
        Optional<List<Image>> imageList = imageRepository.findAll(new ImageByPostIdSpecification(post.get().getId()));
        Optional<Like> like = likeRepository
                .findOne(new LikeByUserIdSpecification((int) req.getSession().getAttribute("id"), id));

        int firstTagIndex = post.get().getText().indexOf("#");
        String[] tags = post.get().getText().substring(firstTagIndex, post.get().getText().length()).split(" ");
        post.get().setText(post.get().getText().substring(0, firstTagIndex));

        req.setAttribute("title", "Post");
        req.setAttribute("style", "post");
        req.setAttribute("post", post.get());
        req.setAttribute("user", user.get());
        req.setAttribute("id", req.getSession().getAttribute("id"));
        req.setAttribute("comments", commentList.get());
        req.setAttribute("images", imageList.get());
        req.setAttribute("tags", tags);
        req.setAttribute("isLiked", String.valueOf(like.isPresent()));
        req.setAttribute("js", "post");
        req.getRequestDispatcher("/post.ftlh").forward(req, resp);
    }
}
