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
    public User update(User model) {
        return null;
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public Optional<List<User>> query(Specification specification) {
        try {
            ResultSet rs = specification.generateSql("SELECT * FROM user", connection).executeQuery();
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
}
