package com.autogram.model.orm.mapper;

import com.autogram.model.entity.User;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserRowMapper implements IRowMapper<User> {
    @Override
    public User mapRow(ResultSet row) throws SQLException {
        User user = new User();
        user.setId(row.getInt("id"));
        user.setName(row.getString("name"));
        user.setToken(row.getString("token"));
        user.setEmail(row.getString("email"));
        user.setAvatar(row.getString("avatar"));
        user.setLastName(row.getString("last_name"));
        user.setFirstName(row.getString("first_name"));
        user.setHashPassword(row.getString("password_hash"));
        if (row.getString("status") == null) {
            user.setStatus("");
        } else {
            user.setStatus(row.getString("status"));
        }
        if (hasColumn(row, "subscribers")) {
            user.setSubscriber(row.getInt("subscribers"));
        }
        if (hasColumn(row, "subscription")) {
            user.setSubscription(row.getInt("subscription"));
        }
        return user;
    }

    public static boolean hasColumn(ResultSet row, String columnName) throws SQLException {
        ResultSetMetaData rowMetaData = row.getMetaData();
        int columns = rowMetaData.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rowMetaData.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }
}
