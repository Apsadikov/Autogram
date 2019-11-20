package com.autogram.controller;

import com.autogram.model.entity.Message;
import com.autogram.model.orm.repository.DialogRepository;
import com.autogram.model.orm.specification.dialog.DialogByUserIdSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DialogServlet extends HttpServlet {
    private DialogRepository dialogRepository;

    @Override
    public void init() throws ServletException {
        dialogRepository = new DialogRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<List<Message>> dialogList = dialogRepository
                .findAll(new DialogByUserIdSpecification((int) req.getSession().getAttribute("id")));

        req.setAttribute("title", "Dialog");
        req.setAttribute("style", "dialog");
        req.setAttribute("dialogs", dialogList.get());
        req.getRequestDispatcher("/dialog.ftlh").forward(req, resp);
    }
}
