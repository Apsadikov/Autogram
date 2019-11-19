package com.autogram.model.orm.specification.subscription;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubscriptionUserSpecification implements Specification {
    private int userId;

    public SubscriptionUserSpecification(int userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT user.* FROM subscription INNER JOIN user ON user.id = subscription.user_id " +
                    "WHERE subscriber_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
