package com.solvd.socialnetwork.impl;

import com.solvd.socialnetwork.dao.IUserDAO;
import com.solvd.socialnetwork.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

    @Override
    public User getById(int id) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUsername(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            System.out.println("Error");
        } finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
        return user;    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (username, firstName, lastName, email, password, profilePicture, birthDate, registerDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getFirstName());
            stm.setString(3, user.getLastName());
            stm.setString(4, user.getEmail());
            stm.setString(5, user.getPassword());
            stm.setString(6, user.getProfilePicture());
            stm.setString(7, user.getBirthDate());
            stm.setString(8, user.getRegisterDate());
            stm.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET username = ? WHERE id = ?";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, user.getUsername());
            stm.setLong(2, user.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "UPDATE FROM users WHERE id = ?";
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
