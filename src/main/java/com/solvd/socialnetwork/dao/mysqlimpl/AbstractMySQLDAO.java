package com.solvd.socialnetwork.dao.mysqlimpl;

import java.sql.Connection;

public class AbstractMySQLDAO {

    protected Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    protected void releaseConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

}
