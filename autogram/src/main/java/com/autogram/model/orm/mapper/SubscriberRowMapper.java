package com.autogram.model.orm.mapper;

import com.autogram.model.entity.Subscriber;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriberRowMapper implements IRowMapper<Subscriber>  {

    @Override
    public Subscriber mapRow(ResultSet row) throws SQLException {
        Subscriber subscriber = new Subscriber();
        subscriber.setId(row.getInt("id"));
        subscriber.setUserId(row.getInt("user_id"));
        subscriber.setSubscriberId(row.getInt("subscriber_id"));
        return subscriber;
    }
}
