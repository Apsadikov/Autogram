package com.autogram.model.orm.specification.post;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostByIdSpecification implements Specification {
    private int id;

    public PostByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT post.*, image.src AS preview, " +
                    "(SELECT COUNT(*) FROM `like` WHERE post_id = ?) as `likes` FROM post " +
                    "INNER JOIN image ON post.id = image.post_id WHERE post.id = ? GROUP BY post.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
