package com.autogram.servlet;

import com.autogram.util.EnvConst;
import com.autogram.util.FileUploader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Test extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileUploader fileUploader = new FileUploader(EnvConst.FILE_STORAGE_PATH,
                "jpg", new String[]{"jpg", "png", "jpeg"}, 1024 * 1024 * 2);
//        fileUploader.upload(req.getPart("images"), new FileUploader.FileUploadCallback() {
//            @Override
//            public void onError(FileUploader.FileError error) {
//                System.out.println(error.getCause());
//            }
//
//            @Override
//            public void onSuccess(File file) {
//                System.out.println(file.getName());
//            }
//        });
        fileUploader.upload(req.getParts(), new FileUploader.FilesUploadCallback() {
            @Override
            public void onError(FileUploader.FileError error) {
                System.out.println(error.getCause());
            }

            @Override
            public void onSuccess(ArrayList<File> files) {
                files.stream().forEach(file -> System.out.println(file.getName()));
            }
        }, "images");
    }
}
