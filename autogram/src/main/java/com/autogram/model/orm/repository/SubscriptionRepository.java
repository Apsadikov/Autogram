package com.autogram.model.orm.repository;

import com.autogram.model.entity.Subscriber;
import com.autogram.model.orm.mapper.SubscriberRowMapper;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionRepository implements IRepository<Subscriber> {
    private Connection connection;
    private SubscriberRowMapper subscriberRowMapper;

    public SubscriptionRepository(Connection connection) {
        this.connection = connection;
        subscriberRowMapper = new SubscriberRowMapper();

    }

    @Override
    public Subscriber create(Subscriber model) {
        String sqlQuery = "INSERT INTO subscription(user_id, subscriber_id) VALUE (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getSubscriberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public Subscriber update(Subscriber model) {
        return null;
    }

    @Override
    public void delete(Subscriber model) {

    }

    @Override
    public Optional<List<Subscriber>> findAll(Specification specification) {
        try {
            ResultSet rs = specification.generateSql(connection).executeQuery();
            List<Subscriber> subscribers = new ArrayList<>();
            while (rs.next()) {
                subscribers.add(subscriberRowMapper.mapRow(rs));
            }
            return Optional.of(subscribers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Subscriber> findOne(Specification specification) {
        return Optional.empty();
    }
}
