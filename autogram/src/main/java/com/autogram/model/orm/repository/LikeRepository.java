package com.autogram.model.orm.repository;

import com.autogram.model.entity.Like;
import com.autogram.model.orm.mapper.LikeRowMapper;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LikeRepository implements IRepository<Like> {
    private Connection connection;
    private LikeRowMapper likeRowMapper;

    public LikeRepository(Connection connection) {
        this.connection = connection;
        likeRowMapper = new LikeRowMapper();
    }

    @Override
    public Like create(Like model) {
        String sqlQuery = "INSERT INTO `like` (user_id, post_id) VALUE (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getPostId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public Like update(Like model) {
        return null;
    }

    @Override
    public void delete(Like model) {
        String sqlQuery = "DELETE FROM `like` WHERE post_id = ? AND user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, model.getPostId());
            stmt.setInt(2, model.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<List<Like>> findAll(Specification specification) {
        return Optional.empty();
    }

    @Override
    public Optional<Like> findOne(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            if (rs.next()) {
                Like like = likeRowMapper.mapRow(rs);
                return Optional.of(like);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
