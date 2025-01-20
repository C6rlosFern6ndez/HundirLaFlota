package com.mycompany.hundirlaflotaserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConection {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3336/HundirLaFlota";
        String username = "root";
        String password = "123456";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexión exitosa!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}