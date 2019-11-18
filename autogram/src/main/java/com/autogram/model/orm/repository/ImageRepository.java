package com.autogram.model.orm.repository;

import com.autogram.model.entity.Image;
import com.autogram.model.entity.Post;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ImageRepository implements IRepository<Post> {
    private Connection connection;

    public ImageRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(List<Image> imageList) {
        String insertImage = "INSERT INTO image(post_id, src) VALUES ";
        for (int i = 0; i < imageList.size(); i++) {
            insertImage += "(?, ?),";
        }
        insertImage = insertImage.substring(0, insertImage.length() - 1);
        try {
            int index = 1;
            int tagIndex = 0;
            PreparedStatement preparedStatement = connection.prepareStatement(insertImage);
            for (int i = 0; i < imageList.size(); i++) {
                preparedStatement.setInt(index++, imageList.get(tagIndex).getPostId());
                preparedStatement.setString(index++, imageList.get(tagIndex).getSrc());
                tagIndex++;
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post create(Post model) {
        return null;
    }

    @Override
    public Post update(Post model) {
        return null;
    }

    @Override
    public void delete(Post model) {

    }

    @Override
    public Optional<List<Post>> query(Specification specification) {
        return Optional.empty();
    }
}
