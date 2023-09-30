package com.snowlightpay;

import java.sql.*;
import java.util.Random;

public class DummyDataGenerator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/snowlight_pay?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "mysqluser";
    private static final String Db_PASSWORD = "mysqlpw";

    private static final String[] ADDRESSES = {"강남구", "관악구", "서초구"};

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, Db_PASSWORD);

            generateDummyData(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateDummyData(Connection conn) throws SQLException {
//        PreparedStatement pstmtUsingDelete = conn.prepareStatement("DELETE FROM membership");
//        pstmtUsingDelete.executeUpdate();

        String insertQuery = "INSERT INTO membership (membership_id, address, email, is_corp, is_valid, name) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Random random = new Random();

        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        int numberOfDummyData = 10000;

        for (int i = 0; i <= numberOfDummyData; i++) {
            System.out.println(i);
            pstmt.setLong(1, i);
            pstmt.setString(2, ADDRESSES[random.nextInt(ADDRESSES.length)]);
            pstmt.setString(3, "email_" + i + "@example.com");
            pstmt.setBoolean(4, random.nextBoolean());
            pstmt.setBoolean(5, random.nextBoolean());
            pstmt.setString(6, "Uesr " + i);

            pstmt.executeUpdate();
        }

        pstmt.close();
    }
}
