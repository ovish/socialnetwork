package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IPostDAO;
import com.solvd.socialnetwork.model.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends AbstractMySQLDAO implements IPostDAO {

    private static final Logger LOGGER = LogManager.getLogger(PostDAO.class);

    @Override
    public Post getById(Long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Post post = new Post();
                    post.setUserId(resultSet.getLong("userId"));
                    post.setContent(resultSet.getString("content"));
                    post.setMediaType(resultSet.getString("media_type"));
                    return post;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        return null;
    }

        @Override
    public void save(Post post) {
        String sql = "INSERT INTO posts (userId, content, mediaType, mediaUrl, createdDate) VALUES (?, ?, ?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, post.getUserId());
            stm.setString(2, post.getContent());
            stm.setString(3, post.getMediaType());
            stm.setString(4, post.getMediaUrl());
            stm.setTimestamp(5, Timestamp.valueOf(post.getCreatedDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE posts SET content = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, post.getContent());
            stm.setLong(2, post.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public List<Post> findByUserId(Long userId) {
        String sql = "SELECT * FROM posts WHERE user_id = ?";
        Connection con = getConnection();
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                            Post post = new Post();
                            post.setUserId(rs.getLong("userId"));
                            post.setContent(rs.getString("content"));
                            post.setMediaType(rs.getString("media_type"));
                            posts.add(post);
                    }
                }
        } catch (SQLException e) {
            LOGGER.error("Error finding identity documents by customer id", e);
        } finally {
            releaseConnection(con);
        }
        return posts;
    }
}
