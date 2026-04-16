package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.ILikeDAO;
import com.solvd.socialnetwork.model.Like;

import java.sql.*;

public class LikeDAO extends AbstractMySQLDAO implements ILikeDAO {


    @Override
    public Like getById(Long id) {
        String sql = "SELECT * FROM likes WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Like like = new Like();
                    like.setPostId(resultSet.getLong("post_id"));
                    like.setCommentId(resultSet.getLong("comment_id"));
                    like.setUserId(resultSet.getLong("user_id"));
                    return like;
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
    public void save(Like like) {
        String sql = "INSERT INTO likes (id, postId, commentId, userId, createDate) VALUES (?, ?, ?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, like.getId());
            stm.setLong(2, like.getPostId());
            stm.setLong(3, like.getCommentId());
            stm.setLong(4, like.getUserId());
            stm.setTimestamp(5, Timestamp.valueOf(like.getCreateDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Like like) {
        String sql = "UPDATE likes SET createDate = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setTimestamp(1, Timestamp.valueOf(like.getCreateDate()));
            stm.setLong(2, like.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM likes WHERE id = ?";
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
}
