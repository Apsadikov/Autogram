package com.autogram.controller;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.user.UserByEmailSpecification;
import com.autogram.util.DBConnection;
import com.autogram.util.FileUploader;
import com.autogram.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SignupServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository(DBConnection.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("errors", req.getAttribute("errors"));
        req.setAttribute("name", req.getParameter("name"));
        req.setAttribute("first_name", req.getAttribute("first_name"));
        req.setAttribute("last_name", req.getAttribute("last_name"));
        req.setAttribute("email", req.getAttribute("email"));
        req.setAttribute("password", req.getAttribute("password"));

        setTemplate(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = new Validator();

        String password = validator.value("password", req.getParameter("password"))
                .isRequired().onError("Введите пароль")
                .isPassword().onError("Пароль должен быть не меньше 8 символов и не больше 32. " +
                        "Должен содержать только цифры и буквы латинского алфавита")
                .validate();
        String email = validator.value("email", req.getParameter("email"))
                .isRequired().onError("Введите Email")
                .isEmail().onError("Некоректный Email")
                .validate();
        String name = validator.value("name", req.getParameter("name"))
                .isRequired().onError("Введите никнейм")
                .length(1, 45)
                .onError("Никнейм не должен быть длинее 45 символов")
                .validate();
        String firstName = validator.value("first_name", req.getParameter("first_name"))
                .isRequired().onError("Введите имя")
                .length(1, 45)
                .onError("Имя не должен быть длинее 45 символов")
                .validate();
        String lastName = validator.value("last_name", req.getParameter("last_name"))
                .isRequired().onError("Введите фамилию")
                .length(1, 45)
                .onError("Фамилия не должена быть длинее 45 символов")
                .validate();

        if (req.getPart("file").getSubmittedFileName() == null
                || req.getPart("file").getSubmittedFileName().length() == 0) {
            validator.addError("file", "Загрузите аватар");
        }

        if (validator.getErrorList().size() != 0) {
            req.setAttribute("errors", validator.getErrorList());
            req.setAttribute("name", name);
            req.setAttribute("first_name", firstName);
            req.setAttribute("last_name", lastName);
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            setTemplate(req, resp);
        } else {
            Optional<List<User>> user = userRepository.findAll(new UserByEmailSpecification(email));
            if (user.isPresent() && user.get().size() == 0) {
                FileUploader fileUploader = new FileUploader(getServletContext(),
                        new File(System.getProperty("user.dir") + "/config/file-uploader.properties"));
                fileUploader.upload(req.getPart("file"), new FileUploader.FileUploadCallback() {
                    @Override
                    public void onError(FileUploader.FileError error) {
                        validator.addError("file", error.getCause());
                        req.setAttribute("errors", validator.getErrorList());
                        req.setAttribute("name", name);
                        req.setAttribute("first_name", firstName);
                        req.setAttribute("last_name", lastName);
                        req.setAttribute("email", email);
                        req.setAttribute("password", password);
                        req.setAttribute("errors", validator.getErrorList());
                        setTemplate(req, resp);
                    }

                    @Override
                    public void onSuccess(File file) {
                        User newUser = new User();
                        newUser.setName(name);
                        newUser.setFirstName(firstName);
                        newUser.setLastName(lastName);
                        newUser.setEmail(email);
                        newUser.setAvatar(file.getName());
                        newUser.setPassword(password);
                        userRepository.create(newUser);
                        try {
                            resp.sendRedirect("/login");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                validator.addError("email", "Такой email уже используется");
                req.setAttribute("name", name);
                req.setAttribute("first_name", firstName);
                req.setAttribute("last_name", lastName);
                req.setAttribute("email", email);
                req.setAttribute("password", password);
                req.setAttribute("errors", validator.getErrorList());
                setTemplate(req, resp);
            }
        }
    }

    private void setTemplate(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("title", "Sign up");
        req.setAttribute("style", "signup");
        req.setAttribute("js", "signup");
        try {
            req.getRequestDispatcher("/signup.ftlh").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
