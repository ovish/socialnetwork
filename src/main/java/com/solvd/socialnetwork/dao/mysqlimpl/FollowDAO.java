package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IFollowDAO;
import com.solvd.socialnetwork.model.Follow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class FollowDAO extends AbstractMySQLDAO implements IFollowDAO {

    private static final Logger logger = LogManager.getLogger(FollowDAO.class);

    @Override
    public Follow get(Long followedId, Long followingId) {
        String sql = "SELECT * FROM follows WHERE followed_id = ? AND following_id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, followedId);
            stm.setLong(2, followingId);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Follow f = new Follow();
                    f.setFollowedId(resultSet.getLong("followed"));
                    f.setFollowingId(resultSet.getLong("following"));
                    return f;
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting by id", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void follow(Follow f) {
        String sql = "INSERT INTO follows (followed_id, following_id, createDate) VALUES (?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, f.getFollowedId());
            stm.setLong(2, f.getFollowingId());
            stm.setTimestamp(3, Timestamp.valueOf(f.getCreateDate()));

            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error following", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void unfollow(Long followedId, Long followingId) {
        String sql = "DELETE FROM follows WHERE followed_id = ? AND following_id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, followedId);
            stm.setLong(2, followingId);

            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error unfollowing", e);
        } finally {
            releaseConnection(con);
        }

    }
}
