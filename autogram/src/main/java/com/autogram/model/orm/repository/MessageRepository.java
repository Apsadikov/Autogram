package com.autogram.model.orm.repository;

import com.autogram.model.entity.Message;
import com.autogram.model.orm.mapper.MessageRowMapper;
import com.autogram.model.orm.specification.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepository implements IRepository<Message> {
    private MessageRowMapper messageRowMapper;
    private Connection connection;

    public MessageRepository(Connection connection) {
        this.connection = connection;
        messageRowMapper = new MessageRowMapper();
    }

    @Override
    public Message create(Message model) {
        String sqlQuery = "INSERT INTO message (`from`, `to`, text) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, model.getFromId());
            stmt.setInt(2, model.getToId());
            stmt.setString(3, model.getText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public Message update(Message model) {
        return null;
    }

    @Override
    public void delete(Message model) {

    }

    @Override
    public Optional<List<Message>> findAll(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            List<Message> messageList = new ArrayList<>();
            while (rs.next()) {
                messageList.add(messageRowMapper.mapRow(rs));
            }
            return Optional.of(messageList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Message> findOne(Specification specification) {
        return Optional.empty();
    }
}
