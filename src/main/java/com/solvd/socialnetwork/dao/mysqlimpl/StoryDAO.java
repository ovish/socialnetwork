package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IStoryDAO;
import com.solvd.socialnetwork.model.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class StoryDAO extends AbstractMySQLDAO implements IStoryDAO {

    private static final Logger logger = LogManager.getLogger(StoryDAO.class);

    @Override
    public Story getById(Long id) {
        String sql = "SELECT * FROM stories WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Story story = new Story();
                    story.setUserId(resultSet.getLong("user_id"));
                    story.setMediaUrl(resultSet.getString("media"));
                    return story;
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting story by id", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void save(Story story) {
        String sql = "INSERT INTO stories (id, userId, mediaUrl, createDate, expireDate) VALUES (?, ?, ?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, story.getId());
            stm.setLong(2, story.getUserId());
            stm.setString(3, story.getMediaUrl());
            stm.setTimestamp(4, Timestamp.valueOf(story.getCreateDate()));
            stm.setTimestamp(5, Timestamp.valueOf(story.getExpireDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving story", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Story story) {
        String sql = "UPDATE stories SET mediaUrl = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, story.getMediaUrl());
            stm.setLong(2, story.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating story", e);
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM stories WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting story", e);
        } finally {
            releaseConnection(con);
        }

    }
}
