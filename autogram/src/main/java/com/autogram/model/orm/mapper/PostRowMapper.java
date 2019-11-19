package com.autogram.model.orm.mapper;

import com.autogram.model.entity.Post;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PostRowMapper implements IRowMapper<Post> {
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
    public Post mapRow(ResultSet row) throws SQLException {
        Post post = new Post();
        post.setId(row.getInt("id"));
        post.setText(row.getString("text"));
        post.setUserId(row.getInt("user_id"));
        post.setPreview(row.getString("preview"));
        if (hasColumn(row, "likes")) {
            post.setLikes(Integer.parseInt(row.getString("likes")));
        }
        return post;
    }
}
