package com.solvd.socialnetwork.dao.mysqlimpl;

import com.solvd.socialnetwork.dao.IHashtagDAO;
import com.solvd.socialnetwork.model.Chat;
import com.solvd.socialnetwork.model.Hashtag;
import com.solvd.socialnetwork.model.PostHashtag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HashtagDAO extends AbstractMySQLDAO implements IHashtagDAO {

    @Override
    public Hashtag getById(Long id) {
        String sql = "SELECT * FROM hashtags WHERE id = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Hashtag hashtag = new Hashtag();
                    hashtag.setName(resultSet.getString("name"));
                    return hashtag;
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
    public void save(Hashtag hashtag) {
        String sql = "INSERT INTO hashtags (id, name) VALUES (?, ?)";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setLong(1, hashtag.getId());
            stm.setString(2, hashtag.getName());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
    }

    @Override
    public void update(Hashtag hashtag) {
        String sql = "UPDATE hashtags SET name = ? WHERE id = ?";
        Connection con = getConnection();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, hashtag.getName());
            stm.setLong(2, hashtag.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }

    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM hashtags WHERE id = ?";
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

    @Override
    public List<Hashtag> getAll() {
        List<Hashtag> list = new ArrayList<>();
        String sql = "SELECT * FROM hashtags";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Hashtag hashtag = new Hashtag();
                hashtag.setId(resultSet.getLong("hashtag_id"));
                hashtag.setName(resultSet.getString("hashtag_name"));
                list.add(hashtag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        return list;
    }

    @Override
    public Hashtag getByName(String name) {
        String sql = "SELECT * FROM hashtags WHERE name = ?";
        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, name);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Hashtag hashtag = new Hashtag();
                    hashtag.setName(resultSet.getString("name"));
                    return hashtag;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(con);
        }
        return null;
    }
}
