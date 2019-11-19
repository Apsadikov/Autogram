package com.autogram.model.orm.mapper;

import com.autogram.model.entity.Like;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeRowMapper implements IRowMapper<Like> {
    @Override
    public Like mapRow(ResultSet row) throws SQLException {
        Like like = new Like();
        like.setId(row.getInt("id"));
        like.setUserId(row.getInt("user_id"));
        like.setPostId(row.getInt("post_id"));
        return like;
    }
}
