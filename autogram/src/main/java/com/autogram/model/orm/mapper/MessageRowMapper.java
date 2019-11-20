package com.autogram.model.orm.mapper;

import com.autogram.model.entity.Message;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MessageRowMapper implements IRowMapper<Message> {

    public static boolean hasColumn(ResultSet row, String columnName) throws SQLException {
        ResultSetMetaData rowMetaData = row.getMetaData();
        int columns = rowMetaData.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rowMetaData.getColumnLabel(x))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Message mapRow(ResultSet row) throws SQLException {
        Message message = new Message();
        if (hasColumn(row, "avatar")) {

            message.setAvatar(row.getString("avatar"));
        }
        if (hasColumn(row, "name")) {
            message.setName(row.getString("name"));
        }
        message.setText(row.getString("text"));
        message.setFromId(row.getInt("from"));
        return message;
    }
}
