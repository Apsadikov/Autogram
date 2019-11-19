package com.autogram.model.orm.repository;

import com.autogram.model.entity.Comment;
import com.autogram.model.orm.mapper.CommentRowMapper;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepository implements IRepository<Comment> {
    private Connection connection;
    private CommentRowMapper commentRowMapper;

    public CommentRepository(Connection connection) {
        this.connection = connection;
        commentRowMapper = new CommentRowMapper();
    }

    @Override
    public Comment create(Comment model) {
        String sqlQuery = "INSERT INTO comment(user_id, post_id, text) VALUE (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getPostId());
            stmt.setString(3, model.getText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public Comment update(Comment model) {
        return null;
    }

    @Override
    public void delete(Comment model) {

    }

    @Override
    public Optional<List<Comment>> findAll(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            List<Comment> commentList = new ArrayList<>();
            while (rs.next()) {
                commentList.add(commentRowMapper.mapRow(rs));
            }
            return Optional.of(commentList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Comment> findOne(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            rs.next();
            Comment comment = commentRowMapper.mapRow(rs);
            return Optional.of(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
