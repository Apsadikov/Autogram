package com.autogram.model.orm.repository;

import com.autogram.model.entity.Post;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostRepositiory implements IRepository<Post> {
    private Connection connection;

    public PostRepositiory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Post create(Post post) {
        String sqlQuery = "INSERT INTO post(user_id, text) VALUE (?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, post.getId());
            stmt.setString(2, post.getText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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
