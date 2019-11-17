package com.autogram.model.orm.specification.user;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserIdSpecification implements Specification {
    private int id;
    private String token;

    public UserIdSpecification(int id, String token) {
        this.id = id;
        this.token = token;
    }

    @Override
    public PreparedStatement generateSql(String partOfQuery, Connection connection) {
        try {
            String sql = partOfQuery + " WHERE id = ? AND token = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,token);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
