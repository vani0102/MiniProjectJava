package com.mpj.miniproj.example.demo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.ResultSet;

import com.mpj.miniproj.example.demo.Response;

public class SessionDAO {

    public static void saveSession(String text, int fumbles, String sentiment) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO sessions (text, fumbles, sentiment, created_at) VALUES (?, ?, ?, CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, text);
            stmt.setInt(2, fumbles);
            stmt.setString(3, sentiment);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getStreak() {
        int streak = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT DISTINCT created_at FROM sessions ORDER BY created_at DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            LocalDate today = LocalDate.now();

            while (rs.next()) {
                LocalDate date = rs.getDate("created_at").toLocalDate();

                if (date.equals(today.minusDays(streak))) {
                    streak++;
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return streak;
    }

    public static List<Response> getAllSessions() {
        List<Response> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM sessions";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Response(
                        rs.getString("text"),
                        rs.getInt("fumbles"),
                        rs.getString("sentiment")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}