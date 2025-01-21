package com.mycompany.hundirlaflotaserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/HundirLaFlota";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(query);
        close(conn, stmt, null);
        return result;
    }
}