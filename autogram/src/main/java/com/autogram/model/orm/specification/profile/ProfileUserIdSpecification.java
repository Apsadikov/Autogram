package com.autogram.model.orm.specification.profile;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileUserIdSpecification implements Specification {
    private int id;

    public ProfileUserIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT user.*, " +
                    "(SELECT COUNT(*) FROM user INNER JOIN subscription ON user.id = subscription.subscriber_id WHERE user.id = ?)" +
                    " AS 'subscription', " +
                    "(SELECT COUNT(*) FROM user INNER JOIN subscription ON user.id = subscription.user_id WHERE user.id = ?)" +
                    " AS 'subscribers'" +
                    " FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, id);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
