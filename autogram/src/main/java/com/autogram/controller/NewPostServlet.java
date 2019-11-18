package com.autogram.controller;

import com.autogram.model.entity.Image;
import com.autogram.model.entity.Post;
import com.autogram.model.entity.Tag;
import com.autogram.model.orm.repository.ImageRepository;
import com.autogram.model.orm.repository.PostRepository;
import com.autogram.model.orm.repository.TagRepository;
import com.autogram.util.DBConnection;
import com.autogram.util.FileUploader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewPostServlet extends HttpServlet {
    private PostRepository postRepository;
    private TagRepository tagRepository;
    private ImageRepository imageRepository;

    public NewPostServlet() {
        postRepository = new PostRepository(DBConnection.getInstance());
        tagRepository = new TagRepository(DBConnection.getInstance());
        imageRepository = new ImageRepository(DBConnection.getInstance());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPart("file").getSubmittedFileName() != null
                && req.getPart("file").getSubmittedFileName().length() != 0) {
            FileUploader fileUploader = new FileUploader(getServletContext(),
                    new File(System.getProperty("user.dir") + "/config/file-uploader.properties"));
            List<File> imageSrcs = new ArrayList<>();
            fileUploader.upload(req.getParts(), new FileUploader.FilesUploadCallback() {
                @Override
                public void onError(FileUploader.FileError error) {
                }

                @Override
                public void onSuccess(ArrayList<File> files) {
                    imageSrcs.addAll(files);
                }
            }, "file");
            String text = req.getParameter("text");

            Post post = new Post();
            post.setText(text);
            post.setUserId((int) req.getSession().getAttribute("id"));
            int id = postRepository.create(post).getId();

            List<Image> images = new ArrayList<>();
            for (File file : imageSrcs) {
                Image image = new Image();
                image.setPostId(id);
                image.setSrc(file.getName());
                images.add(image);
            }
            imageRepository.create(images);

            if (text.indexOf("#") != -1) {
                String[] tags = text.substring(text.indexOf("#"), text.length()).split(" ");

                List<Tag> tagList = new ArrayList<>();
                for (int i = 0; i < tags.length; i++) {
                    Tag tag = new Tag();
                    tag.setName(tags[i]);
                    tag.setPostId(id);
                    tagList.add(tag);
                }
                tagRepository = new TagRepository(DBConnection.getInstance());
                tagRepository.create(tagList);
            }
        }
        resp.sendRedirect("http://localhost:8080/profile");
    }
}
