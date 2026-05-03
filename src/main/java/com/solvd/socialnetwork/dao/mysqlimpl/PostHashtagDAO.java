package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IPostHashtagDAO;
import com.solvd.socialnetwork.model.PostHashtag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostHashtagDAO extends AbstractMySQLDAO implements IPostHashtagDAO {

    private static final Logger logger = LogManager.getLogger(PostHashtagDAO.class);

    @Override
    public PostHashtag getById(Long postId, Long hashtagId) {
        String sql = "SELECT * FROM post_hashtags WHERE post_id = ? AND hashtag_id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, postId);
            stm.setLong(2, hashtagId);

            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    PostHashtag postHashtag = new PostHashtag();
                    postHashtag.setPostId(resultSet.getLong("post_id"));
                    postHashtag.setHashtagId(resultSet.getLong("hashtag_id"));
                    return postHashtag;
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting post hashtag by id", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void save(PostHashtag postHashtag) {
        String sql = "INSERT INTO post_hashtags (post_id, hashtag_id) VALUES (?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, postHashtag.getPostId());
            stm.setLong(2, postHashtag.getHashtagId());

            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving post hashtag", e);
        } finally {
            releaseConnection(con);
        }
    }


    @Override
    public void deleteById(Long postId, Long hashtagId) {
        String sql = "DELETE FROM post_hashtags WHERE post_id = ? AND hashtag_id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, postId);
            stm.setLong(2, hashtagId);

            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting post hashtag", e);
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public List<PostHashtag> getAll() {
        List<PostHashtag> list = new ArrayList<>();
        String sql = "SELECT * FROM post_hashtags";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql);
             ResultSet resultSet = stm.executeQuery()) {
                while (resultSet.next()) {
                    PostHashtag postHashtag = new PostHashtag();
                    postHashtag.setPostId(resultSet.getLong("post_id"));
                    postHashtag.setHashtagId(resultSet.getLong("hashtag_id"));
                    list.add(postHashtag);
                }
        } catch (SQLException e) {
            logger.error("Error getting all post hashtags", e);
        } finally {
            releaseConnection(con);
        }
        return list;
    }

}
