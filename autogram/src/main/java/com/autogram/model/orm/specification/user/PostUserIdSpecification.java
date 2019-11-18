package com.autogram.model.orm.specification.user;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostUserIdSpecification implements Specification {
    private int id;

    public PostUserIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT post.*, image.src AS preview FROM post INNER JOIN image ON " +
                    "post.id = image.post_id WHERE post.user_id = ? GROUP BY post.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
