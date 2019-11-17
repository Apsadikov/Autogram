package com.autogram.model.orm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
