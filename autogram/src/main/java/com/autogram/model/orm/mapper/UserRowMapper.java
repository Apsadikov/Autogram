package com.autogram.model.orm.mapper;

import com.autogram.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements IRowMapper<User> {
    @Override
    public User mapRow(ResultSet row) throws SQLException {
        User user = new User();
        user.setId(row.getInt("id"));
        user.setName(row.getString("name"));
        user.setEmail(row.getString("email"));
        user.setStatus(row.getString("status"));
        user.setAvatar(row.getString("avatar"));
        user.setLastName(row.getString("last_name"));
        user.setFirstName(row.getString("first_name"));
        user.setHashPassword(row.getString("password_hash"));
        return user;
    }
}
