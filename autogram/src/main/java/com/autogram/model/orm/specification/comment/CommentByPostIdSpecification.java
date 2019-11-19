package com.autogram.model.orm.specification.comment;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentByPostIdSpecification implements Specification {
    private int postId;

    public CommentByPostIdSpecification(int postId) {
        this.postId = postId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT comment.*, user.name AS 'author_name', user.avatar AS 'author_avatar' FROM comment " +
                    "INNER JOIN user ON user.id = comment.user_id WHERE comment.post_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, postId);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
