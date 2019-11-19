package com.autogram.model.orm.mapper;

import com.autogram.model.entity.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements IRowMapper<Image> {
    @Override
    public Image mapRow(ResultSet row) throws SQLException {
        Image image = new Image();
        image.setSrc(row.getString("src"));
        image.setPostId(row.getInt("post_id"));
        image.setId(row.getInt("id"));
        return image;
    }
}
