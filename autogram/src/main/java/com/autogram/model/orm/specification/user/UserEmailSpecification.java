package com.autogram.model.orm.specification.user;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserEmailSpecification implements Specification {
    private String email;

    public UserEmailSpecification(String email) {
        this.email = email;
    }

    @Override
    public PreparedStatement generateSql(String partOfQuery, Connection connection) {
        try {
            String sql = partOfQuery + " WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
