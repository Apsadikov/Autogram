package com.autogram.model.orm.mapper;

import com.autogram.model.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements IRowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet row) throws SQLException {
        Comment comment = new Comment();
        comment.setId(row.getInt("id"));
        comment.setPostId(row.getInt("post_id"));
        comment.setUserId(row.getInt("user_id"));
        comment.setText(row.getString("text"));
        comment.setAuthorAvatar(row.getString("author_avatar"));
        comment.setAuthorName(row.getString("author_name"));
        return comment;
    }
}
