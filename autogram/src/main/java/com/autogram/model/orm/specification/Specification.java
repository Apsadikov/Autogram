package com.autogram.model.orm.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface Specification {
    PreparedStatement generateSql(Connection connection);
}
