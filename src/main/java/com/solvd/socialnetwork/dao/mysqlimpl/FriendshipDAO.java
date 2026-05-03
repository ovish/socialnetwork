package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IFriendshipDAO;
import com.solvd.socialnetwork.model.Friendship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class FriendshipDAO extends AbstractMySQLDAO implements IFriendshipDAO {

    private static final Logger logger = LogManager.getLogger(FriendshipDAO.class);

    @Override
    public Friendship getById(Long id) {
        String sql = "SELECT * FROM friendships WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Friendship f = new Friendship();
                    f.setUserFromId(resultSet.getLong("user_from"));
                    f.setUserToId(resultSet.getLong("user_to"));
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
    public void save(Friendship f) {
        String sql = "INSERT INTO friendship (id, userFromId, userToId, requestDate, approveDate) VALUES (?, ?, ?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, f.getId());
            stm.setLong(2, f.getUserFromId());
            stm.setLong(3, f.getUserToId());
            stm.setTimestamp(4, Timestamp.valueOf(f.getRequestDate()));

            if (f.getApproveDate() != null) {
                stm.setTimestamp(5, Timestamp.valueOf(f.getApproveDate()));
            } else {
                stm.setNull(5, Types.TIMESTAMP);
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding friendship", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Friendship f) {
        String sql = "UPDATE friendships SET user_from = ?, user_to=?, request_date=?, approve_date=? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, f.getId());
            stm.setLong(2, f.getUserFromId());
            stm.setLong(3, f.getUserToId());
            stm.setTimestamp(4, Timestamp.valueOf(f.getRequestDate()));
            if (f.getApproveDate() != null) {
                stm.setTimestamp(5, Timestamp.valueOf(f.getApproveDate()));
            } else {
                stm.setNull(5, Types.TIMESTAMP);
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating friendship", e);
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM friendships WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting friendship", e);
        } finally {
            releaseConnection(con);
        }

    }
}
