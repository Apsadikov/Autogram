package com.autogram.model.orm.specification.message;

import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageByUserIdSpecification implements Specification {
    private int fromId;
    private int toId;

    public MessageByUserIdSpecification(int fromId, int toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    @Override
    public PreparedStatement generateSql(Connection connection) {
        try {
            String sql = "SELECT * FROM autogram.message WHERE (`from` = ? AND `to` = ?) " +
                    "OR (`from` = ? AND `to` = ?) ORDER BY id ASC;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fromId);
            preparedStatement.setInt(2, toId);
            preparedStatement.setInt(4, fromId);
            preparedStatement.setInt(3, toId);
            System.out.println(preparedStatement);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
