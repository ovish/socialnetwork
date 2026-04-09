package com.solvd.socialnetwork.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private static final int MAX_CONNECTIONS = 5;
    private static final String URL = "JDBC:mysql://loalhost:3306/socialnetwork";
    private static final String USER = "root";
    private static final String PASSWORD = "00000000";

    public ConnectionPool() {
        pool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);
        try {
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.add(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance () {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
    public Connection getConnection () {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);

        }
    }
}
