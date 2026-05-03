package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IGroupDAO;
import com.solvd.socialnetwork.model.Group;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class GroupDAO extends AbstractMySQLDAO implements IGroupDAO {

    private static final Logger logger = LogManager.getLogger(GroupDAO.class);


    @Override
    public Group getById(Long id) {
        String sql = "SELECT * FROM groups WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Group group = new Group();
                    group.setName(resultSet.getString("name"));
                    group.setDescription(resultSet.getString("description"));
                    return group;
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting group by id", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void save(Group group) {
        String sql = "INSERT INTO groups (id, adminId, name, description, imageUrl, createDate) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, group.getId());
            stm.setLong(2, group.getAdminId());
            stm.setString(3, group.getName());
            stm.setString(4, group.getDescription());
            stm.setString(5, group.getImageUrl());
            stm.setTimestamp(6, Timestamp.valueOf(group.getCreateDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding group", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Group group) {
        String sql = "UPDATE groups SET adminId = ?, name = ?, description = ?, imageUrl = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, group.getAdminId());
            stm.setString(2, group.getName());
            stm.setString(3, group.getDescription());
            stm.setString(4, group.getImageUrl());
            stm.setLong(5, group.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating group", e);
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM groups WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting group", e);
        } finally {
            releaseConnection(con);
        }

    }
}
