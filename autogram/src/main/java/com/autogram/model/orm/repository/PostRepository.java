package com.autogram.model.orm.repository;

import com.autogram.model.entity.Post;
import com.autogram.model.orm.mapper.PostRowMapper;
import com.autogram.model.orm.specification.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository implements IRepository<Post> {
    private Connection connection;
    private PostRowMapper postRowMapper;

    public PostRepository(Connection connection) {
        this.connection = connection;
        postRowMapper = new PostRowMapper();
    }

    @Override
    public Post create(Post post) {
        String sqlQuery = "INSERT INTO post(user_id, text) VALUE (?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getText());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            Post newPost = new Post();
            newPost.setId(rs.getInt(1));
            return newPost;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            List<Post> postList = new ArrayList<>();
            while (rs.next()) {
                postList.add(postRowMapper.mapRow(rs));
            }
            return Optional.of(postList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
