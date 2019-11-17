package com.autogram.model.orm.mapper;

import com.autogram.model.entity.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements IRowMapper<Post> {
    @Override
    public Post mapRow(ResultSet row) throws SQLException {
        Post post = new Post();
        post.setId(row.getInt("id"));
        post.setText(row.getString("text"));
        post.setUserId(row.getInt("user_id"));
        return post;
    }
}
