package com.autogram.model.orm.specification.like;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikeByUserIdSpecification implements Specification {
    private int userId;
    private int postId;

    public LikeByUserIdSpecification(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT * FROM `like` WHERE user_id = ? AND post_id = ? LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, postId);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
