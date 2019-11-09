package com.autogram.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class FileUploader {
    private static final int FILE_NAME_LENGTH = 128;
    private static final String FILE_AVAILABLE_CHARACTERS = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";

    private String destinationPath;
    private String targetExtension;
    private String[] extensionWhiteList;
    private int maxFileSize;

    public FileUploader(String destinationPath, String targetExtension,
                        String[] extensionWhiteList, int maxFileSize) {
        this.destinationPath = destinationPath;
        this.targetExtension = targetExtension;
        this.extensionWhiteList = extensionWhiteList;
        this.maxFileSize = maxFileSize;
    }

    public void upload(Collection<Part> parts, FilesUploadCallback filesUploadCallback, String fileKey) {
        ArrayList<File> files = new ArrayList<>();
        for (Part part : parts) {
            if (part.getName().equals(fileKey)) {
                boolean result = upload(part, new UploadCallback() {
                    @Override
                    public void onError(FileError error) {
                        for (File file : files) {
                            file.delete();
                        }
                        filesUploadCallback.onError(error);
                    }

                    @Override
                    public void onSuccess(File file) {
                        files.add(file);
                    }
                });
                if (!result) return;
            }
        }
        filesUploadCallback.onSuccess(files);
    }

    public void upload(Part part, FileUploadCallback fileUploadCallback) throws IOException {
        upload(part, new UploadCallback() {
            @Override
            public void onError(FileError error) {
                fileUploadCallback.onError(error);
            }

            @Override
            public void onSuccess(File file) {
                fileUploadCallback.onSuccess(file);
            }
        });
    }

    private boolean upload(Part part, UploadCallback uploadCallback) {
        try {
            if (!checkFileExtension(part.getSubmittedFileName())) {
                uploadCallback.onError(new FileError("Invalid file extension"));
                return false;
            }
            File file = new File(getUniqueFileName(targetExtension));
            FileOutputStream fileOutputStream = null;
            fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = part.getInputStream();
            byte data[] = new byte[1024];
            int imageSize = 0;
            int byteCount;
            while ((byteCount = inputStream.read(data, 0, 1024)) != -1
                    && (imageSize += byteCount) < maxFileSize) {
                fileOutputStream.write(data, 0, byteCount);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            if (imageSize >= maxFileSize) {
                file.delete();
                uploadCallback.onError(new FileError("Max image size is: " +
                        String.valueOf(maxFileSize / 1024 / 8) + "KB"));
                return false;
            }
            uploadCallback.onSuccess(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkFileExtension(String submittedFileName) {
        String submittedFileExtensionIndex = submittedFileName.substring(submittedFileName.lastIndexOf(".") + 1,
                submittedFileName.length());
        for (String anExtensionWhiteList : extensionWhiteList) {
            if (anExtensionWhiteList.equals(submittedFileExtensionIndex)) {
                return true;
            }
        }
        return false;
    }

    private String getUniqueFileName(String fileExtension) {
        File file;
        String fileName;
        do {
            Random random = new Random();
            char[] generateFileName = new char[FILE_NAME_LENGTH];
            for (int i = 0; i < FILE_NAME_LENGTH; i++) {
                generateFileName[i] = FILE_AVAILABLE_CHARACTERS.charAt(random.nextInt(FILE_AVAILABLE_CHARACTERS.length()));
            }
            fileName = destinationPath + new String(generateFileName) + "." + fileExtension;
            file = new File(fileName);
        } while (file.exists());
        return fileName;
    }

    private interface UploadCallback {
        void onError(FileError error);

        void onSuccess(File file);
    }

    public interface FilesUploadCallback {
        void onError(FileError error);

        void onSuccess(ArrayList<File> files);
    }


    public interface FileUploadCallback {
        void onError(FileError error);

        void onSuccess(File file);
    }

    public static class FileError {
        String cause;

        private FileError(String cause) {
            this.cause = cause;
        }

        public String getCause() {
            return cause;
        }
    }
}
