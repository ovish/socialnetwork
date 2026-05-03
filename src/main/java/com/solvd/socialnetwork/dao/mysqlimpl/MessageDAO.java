package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IMessageDAO;
import com.solvd.socialnetwork.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MessageDAO extends AbstractMySQLDAO implements IMessageDAO {

    private static final Logger logger = LogManager.getLogger(MessageDAO.class);

    @Override
    public Message getById(Long id) {
        String sql = "SELECT * FROM messages WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Message message = new Message();
                    message.setUserId(resultSet.getLong("user_id"));
                    message.setContent(resultSet.getString("content"));
                    return message;
                }
            }
        } catch (SQLException e) {
            logger.error("Error getting message by id", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public void save(Message message) {
        String sql = "INSERT INTO messages (id, userId, chatId, content, sentDate) VALUES (?, ?, ?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, message.getId());
            stm.setLong(2, message.getUserId());
            stm.setLong(3, message.getChatId());
            stm.setString(4, message.getContent());
            stm.setTimestamp(5, Timestamp.valueOf(message.getSentDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving message", e);
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Message message) {
        String sql = "UPDATE messages SET content = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, message.getContent());
            stm.setLong(2, message.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating message", e);
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM messages WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting message", e);
        } finally {
            releaseConnection(con);
        }

    }
}
