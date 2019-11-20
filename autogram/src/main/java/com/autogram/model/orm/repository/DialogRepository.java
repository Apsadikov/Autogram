package com.autogram.model.orm.repository;

import com.autogram.model.entity.Message;
import com.autogram.model.orm.mapper.MessageRowMapper;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DialogRepository implements IRepository<Message> {
    private Connection connection;
    private MessageRowMapper messageRowMapper;

    public DialogRepository(Connection connection) {
        this.connection = connection;
        messageRowMapper = new MessageRowMapper();
    }

    @Override
    public Message create(Message model) {
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
