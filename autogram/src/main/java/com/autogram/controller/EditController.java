package com.autogram.controller;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.profile.ProfileUserIdSpecification;
import com.autogram.util.DBConnection;
import com.autogram.util.FileUploader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class EditController extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId((int) req.getSession().getAttribute("id"));
        if (req.getParameter("status") != null) {
            user.setStatus(req.getParameter("status"));
        }
        if (req.getPart("file").getSubmittedFileName() != null
                && req.getPart("file").getSubmittedFileName().length() != 0) {
            FileUploader fileUploader = new FileUploader(getServletContext(),
                    new File(System.getProperty("user.dir") + "/config/file-uploader.properties"));
            fileUploader.upload(req.getPart("file"), new FileUploader.FileUploadCallback() {
                @Override
                public void onError(FileUploader.FileError error) {

                }

                @Override
                public void onSuccess(File file) {
                    user.setAvatar(file.getName());
                }
            });
        }

        userRepository.update(user);

        resp.sendRedirect("/profile.ftlh");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<List<User>> user = userRepository
                .findAll(new ProfileUserIdSpecification((int) req.getSession().getAttribute("id")));

        req.setAttribute("title", "Edit profile");
        req.setAttribute("style", "edit");
        req.setAttribute("userProfile", user.get().get(0));
        req.setAttribute("js", "edit");
        req.getRequestDispatcher("/edit.ftlh").forward(req, resp);
    }
}
