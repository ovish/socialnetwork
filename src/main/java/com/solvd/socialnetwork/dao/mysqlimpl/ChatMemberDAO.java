package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IChatMemberDAO;
import com.solvd.socialnetwork.model.ChatMember;

import java.sql.*;
import java.time.LocalDateTime;

public class ChatMemberDAO extends AbstractMySQLDAO implements IChatMemberDAO {

    @Override
    public ChatMember getById(Long id) {
        String sql = "SELECT * FROM chat_members WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    ChatMember chatMember = new ChatMember();
                    chatMember.setUserId(resultSet.getLong("user_id"));
                    return chatMember;
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
    public void save(ChatMember chatMember) {
        String sql = "INSERT INTO chat_members (id, userId, joinDate) VALUES (?, ?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, chatMember.getId());
            stm.setLong(2, chatMember.getUserId());
            stm.setTimestamp(3, Timestamp.valueOf(chatMember.getJoinDate()));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(ChatMember chatMember) {
        String sql = "UPDATE chat_members SET userId = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, chatMember.getUserId());
            stm.setLong(2, chatMember.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM chat_members WHERE id = ?";
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
