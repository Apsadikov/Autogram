package com.autogram.model.orm.specification.post;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostByFriendSpecification implements Specification {
    private int userId;

    public PostByFriendSpecification(int userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT `post_like`.*, image.src AS `preview`, user.name AS `author_name`, user.avatar " +
                    "AS `author_avatar` FROM " +
                    "(SELECT post.*, COUNT(`like`.post_id) as `likes` FROM post " +
                    "LEFT JOIN `like` ON post.id = like.post_id GROUP BY post.id) AS `post_like` " +
                    "INNER JOIN image ON image.post_id = `post_like`.id " +
                    "INNER JOIN subscription ON subscription.subscriber_id = ? " +
                    "INNER JOIN user ON `post_like`.user_id = user.id " +
                    "WHERE subscription.user_id = `post_like`.user_id GROUP BY `post_like`.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
