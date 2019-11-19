package com.autogram.model.orm.repository;

import com.autogram.model.entity.Tag;
import com.autogram.model.orm.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TagRepository implements IRepository<Tag> {
    private Connection connection;

    public TagRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(List<Tag> tagList) {
        String insertTag = "INSERT INTO hashtag(post_id, name) VALUES";
        for (int i = 0; i < tagList.size(); i++) {
            insertTag += "(?, ?),";
        }
        insertTag = insertTag.substring(0, insertTag.length() - 1);
        try {
            int index = 1;
            int tagIndex = 0;
            PreparedStatement preparedStatement = connection.prepareStatement(insertTag);
            for (int i = 0; i < tagList.size(); i++) {
                preparedStatement.setInt(index++, tagList.get(tagIndex).getPostId());
                preparedStatement.setString(index++, tagList.get(tagIndex).getName());
                tagIndex++;
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tag create(Tag model) {
        return null;
    }

    @Override
    public Tag update(Tag model) {
        return null;
    }

    @Override
    public void delete(Tag model) {

    }

    @Override
    public Optional<List<Tag>> findAll(Specification specification) {
        return Optional.empty();
    }

    @Override
    public Optional<Tag> findOne(Specification specification) {
        return Optional.empty();
    }
}
