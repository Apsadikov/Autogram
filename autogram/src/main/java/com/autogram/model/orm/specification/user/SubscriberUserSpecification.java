package com.autogram.model.orm.specification.user;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubscriberUserSpecification implements Specification {
    private int userId;

    public SubscriberUserSpecification(int userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT user.* FROM subscription INNER JOIN user ON user.id = subscription.subscriber_id " +
                    "WHERE user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
