package com.mpj.miniproj.example.demo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SessionDAO {

    public static void saveSession(String text, int fumbles, String sentiment) {

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println("Connection is null ❌");
            return;
        }

        try {
            String query = "INSERT INTO sessions(text, fumbles, sentiment) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, text);
            ps.setInt(2, fumbles);
            ps.setString(3, sentiment);

            ps.executeUpdate();

            System.out.println("Data Saved Successfully ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}