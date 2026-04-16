package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IChatDAO;
import com.solvd.socialnetwork.model.Chat;

import java.sql.*;

public class ChatDAO extends AbstractMySQLDAO implements IChatDAO {

    @Override
    public Chat getById(Long id) {
        String sql = "SELECT * FROM chats WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Chat chat = new Chat();
                    chat.setName(resultSet.getString("name"));
                    return chat;
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
    public void save(Chat chat) {
        String sql = "INSERT INTO chats (id, name, createDate) VALUES (?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, chat.getId());
            stm.setString(2, chat.getName());
            stm.setTimestamp(3, Timestamp.valueOf(chat.getCreateDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Chat chat) {
        String sql = "UPDATE chats SET name = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, chat.getName());
            stm.setLong(2, chat.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM chats WHERE id = ?";
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
