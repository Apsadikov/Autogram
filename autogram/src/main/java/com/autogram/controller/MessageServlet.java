package com.autogram.controller;

import com.autogram.model.entity.Message;
import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.MessageRepository;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.message.MessageByUserIdSpecification;
import com.autogram.model.orm.specification.profile.ProfileUserIdSpecification;
import com.autogram.model.orm.specification.user.UserByIdSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MessageServlet extends HttpServlet {
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessageServlet() {
        messageRepository = new MessageRepository(DBConnection.getInstance());
        userRepository = new UserRepository(DBConnection.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message message = new Message();
        message.setFromId((int) req.getSession().getAttribute("id"));
        message.setToId(Integer.valueOf(req.getParameter("to_id")));
        message.setText(req.getParameter("message"));
        messageRepository.create(message);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fromId = Integer.valueOf(req.getParameter("user_id"));

        Optional<List<Message>> messageList = messageRepository
                .findAll(new MessageByUserIdSpecification(fromId, (int) req.getSession().getAttribute("id")));

        Optional<User> user = userRepository
                .findOne(new ProfileUserIdSpecification(fromId));

        req.setAttribute("title", "Message");
        req.setAttribute("style", "message");
        req.setAttribute("js", "message");
        req.setAttribute("user", user.get());
        req.setAttribute("messages", messageList.get());
        req.getRequestDispatcher("/message.ftlh").forward(req, resp);
    }
}
