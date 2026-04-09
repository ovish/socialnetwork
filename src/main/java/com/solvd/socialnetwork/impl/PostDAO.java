package com.solvd.socialnetwork.impl;

import com.solvd.socialnetwork.dao.IPostDAO;
import com.solvd.socialnetwork.model.Post;
import com.solvd.socialnetwork.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDAO implements IPostDAO {

    @Override
    public Post getById(int id) {
        Post post = new Post();
        String sql = "SELECT * FROM posts WHERE id = ?";
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                post.setUserId(resultSet.getLong("userId"));
                post.setContent(resultSet.getString("content"));
                post.setMediaType(resultSet.getString("media_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
        return post;    }

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO posts (userId, content, mediaType, mediaUrl, createdDate) VALUES (?, ?, ?, ?, ?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, post.getUserId());
            stm.setString(2, post.getContent());
            stm.setString(3, post.getMediaType());
            stm.setString(4, post.getMediaUrl());
            stm.setString(5, post.getCreatedDate());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE posts SET content = ? WHERE id = ?";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, post.getContent());
            stm.setLong(2, post.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "UPDATE FROM posts WHERE id = ?";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }

    }
}
