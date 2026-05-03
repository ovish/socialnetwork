package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IUserDAO;
import com.solvd.socialnetwork.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDAO extends AbstractMySQLDAO implements IUserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    @Override
    public User getById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setUsername(resultSet.getString("username"));
                    return user;
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding user by id", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (id, username, firstName, lastName, email, password, profilePicture, birthDate, registerDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, user.getId());
            stm.setString(2, user.getUsername());
            stm.setString(3, user.getFirstName());
            stm.setString(4, user.getLastName());
            stm.setString(5, user.getEmail());
            stm.setString(6, user.getPassword());
            stm.setString(7, user.getProfilePicture());
            stm.setDate(8, Date.valueOf(user.getBirthDate()));
            stm.setTimestamp(9, Timestamp.valueOf(user.getRegisterDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding new user", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET username = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, user.getUsername());
            stm.setLong(2, user.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating user", e);
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting user", e);
        } finally {
            releaseConnection(con);
        }

    }
}
