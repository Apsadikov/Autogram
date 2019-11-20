package com.autogram.model.orm.specification.dialog;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DialogByUserIdSpecification implements Specification {
    private int userId;

    public DialogByUserIdSpecification(int userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT message.*, user.* FROM message " +
                    "INNER JOIN user ON user.id = message.from WHERE `to` = ? GROUP BY message.from;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
