package com.autogram.model.orm.repository;

import com.autogram.model.entity.Image;
import com.autogram.model.entity.Post;
import com.autogram.model.entity.User;
import com.autogram.model.orm.mapper.ImageRowMapper;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImageRepository implements IRepository<Image> {
    private Connection connection;
    private ImageRowMapper imageRowMapper;

    public ImageRepository(Connection connection) {
        this.connection = connection;
        imageRowMapper = new ImageRowMapper();
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
    public Image create(Image model) {
        return null;
    }

    @Override
    public Image update(Image model) {
        return null;
    }

    @Override
    public void delete(Image model) {

    }

    @Override
    public Optional<List<Image>> findAll(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            List<Image> imageList = new ArrayList<>();
            while (rs.next()) {
                imageList.add(imageRowMapper.mapRow(rs));
            }
            return Optional.of(imageList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Image> findOne(Specification specification) {
        return Optional.empty();
    }
}
