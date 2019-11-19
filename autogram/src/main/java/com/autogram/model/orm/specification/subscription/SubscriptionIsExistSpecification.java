package com.autogram.model.orm.specification.subscription;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubscriptionIsExistSpecification implements Specification {
    private int userId;
    private int subscriberId;

    public SubscriptionIsExistSpecification(int userId, int subscriberId) {
        this.userId = userId;
        this.subscriberId = subscriberId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT * FROM subscription WHERE user_id = ? AND subscriber_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2  , subscriberId);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
