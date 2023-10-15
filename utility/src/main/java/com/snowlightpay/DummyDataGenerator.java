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

//            generateDummyData(connection);
            generateDummyPaymentData(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private static void generateDummyPaymentData (Connection conn) throws SQLException {
        Random random = new Random();

        try {
            String insertQuery = "INSERT INTO payment (payment_id, request_membership_id, request_price, " +
                    "franchise_id, franchise_fee_rate, payment_status, approved_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

            int numberOfTestData = 50;
            for (int i = 0; i < numberOfTestData; i++) {
                // 랜덤 값 생성
                long paymentId = (random.nextInt(900) + 100L); // 100 ~ 999
//                String membershipId = "" + (random.nextInt(900) + 100); // 100 ~ 999
                String membershipId = "" + (random.nextInt(100)  + 1); // 100 ~ 999
                int price = (random.nextInt(9) + 1) * 1000; // 1000 ~ 9000
                String franchiseId =  "" + (random.nextInt(10) + 1L);
                String franchiseFeeRate = String.format("%.2f", random.nextDouble() * 5.0);
                int paymentStatus = 0;
                Date approvedAt = new Date(System.currentTimeMillis() - random.nextInt(10000000));

                preparedStatement.setLong(1, paymentId);
                preparedStatement.setString(2, membershipId);
                preparedStatement.setInt(3, price);
                preparedStatement.setString(4, franchiseId);
                preparedStatement.setString(5, franchiseFeeRate);
                preparedStatement.setInt(6, paymentStatus);
                preparedStatement.setDate(7, new java.sql.Date(approvedAt.getTime()));
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    private static void generateDummyData(Connection conn) throws SQLException {
//        PreparedStatement pstmtUsingDelete = conn.prepareStatement("DELETE FROM membership");
//        pstmtUsingDelete.executeUpdate();

        String insertQuery = "INSERT INTO membership (membership_id, address, email, is_corp, is_valid, name) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Random random = new Random();

        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        int numberOfDummyData = 100;

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
