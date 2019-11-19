package com.autogram.model.orm.specification.post;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostByTagSpecification implements Specification {
    private String tag;

    public PostByTagSpecification(String tag) {
        this.tag = tag;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT `post_like`.*, image.src AS `preview`, user.name AS `author_name`, " +
                    "user.avatar AS `author_avatar` FROM " +
                    "(SELECT post.*, COUNT(`like`.post_id) as `likes` FROM post " +
                    "LEFT JOIN `like` ON post.id = like.post_id GROUP BY post.id) AS `post_like` " +
                    "INNER JOIN hashtag ON `post_like`.id = hashtag.post_id " +
                    "INNER JOIN image ON image.post_id = `post_like`.id " +
                    "INNER JOIN user ON `post_like`.user_id = user.id " +
                    "WHERE hashtag.name = ? GROUP BY `post_like`.id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tag);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
