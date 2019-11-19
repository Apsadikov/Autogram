package com.autogram.model.orm.repository;

import com.autogram.model.entity.User;
import com.autogram.model.orm.mapper.UserRowMapper;
import com.autogram.model.orm.specification.Specification;
import com.autogram.util.PasswordEncrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IRepository<User> {
    private Connection connection;
    private UserRowMapper userRowMapper;

    public UserRepository(Connection connection) {
        this.connection = connection;
        userRowMapper = new UserRowMapper();
    }

    @Override
    public User create(User user) {
        String sqlQuery = "INSERT INTO user(name, first_name, last_name, email, password_hash, avatar, token) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, PasswordEncrypt.generateHash(user.getPassword()));
            stmt.setString(6, user.getAvatar());
            stmt.setString(7, PasswordEncrypt.generateHash(user.getPassword() +
                    user.getName() + new Date().toString()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        if (user.getStatus() != null && user.getAvatar() == null) {
            String sqlQuery = "UPDATE user SET status = ? WHERE user.id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
                stmt.setString(1, user.getStatus());
                stmt.setInt(2, user.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        } else if (user.getStatus() != null && user.getAvatar() != null) {
            String sqlQuery = "UPDATE user SET status = ?, avatar = ? WHERE user.id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
                stmt.setString(1, user.getStatus());
                stmt.setString(2, user.getAvatar());
                stmt.setInt(3, user.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
        return null;
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public Optional<List<User>> findAll(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(userRowMapper.mapRow(rs));
            }
            return Optional.of(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findOne(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            rs.next();
            User user = userRowMapper.mapRow(rs);
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
