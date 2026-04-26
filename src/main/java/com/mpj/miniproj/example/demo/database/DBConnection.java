package com.mpj.miniproj.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/mpj_project";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // change if needed

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected ✅");
            return conn;
        } catch (Exception e) {
            System.out.println("Database Connection Failed ❌");
            e.printStackTrace();
            return null;
        }
    }
}