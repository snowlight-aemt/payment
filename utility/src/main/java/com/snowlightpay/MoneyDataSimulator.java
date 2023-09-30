package com.snowlightpay;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoneyDataSimulator {
    private static final String DECREASE_API_ENDPOINT = "http://localhost:8083/money/decrease-eda";
    private static final String INCREASE_API_ENDPOINT = "http://localhost:8083/money/increase-eda";
    private static final String CREATE_MONEY_API_ENDPOINT = "http://localhost:8083/money/create-member-money";
    private static final String REGISTER_ACCOUNT_API_ENDPOINT = "http://localhost:8082/banking/account/register-eda";

    private static final String[] BANK_NAME = {"KBB", "신한", "우리"};

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        List<Integer> readyMemberList = new ArrayList<>();

        while (true) {
            int amount = random.nextInt(20001) + 1;
            int targetMembershipId = random.nextInt(100) + 1;

            registerAccountSimulator(REGISTER_ACCOUNT_API_ENDPOINT, targetMembershipId);
            createMemberMoneySimulator(CREATE_MONEY_API_ENDPOINT, targetMembershipId);
            Thread.sleep(3000);

            readyMemberList.add(targetMembershipId);

            increaseMemberMoneySimulator(INCREASE_API_ENDPOINT, amount, targetMembershipId);

            amount = random.nextInt(20001) - 10000;
            Integer decreaseTargetMembershipId = readyMemberList.get(random.nextInt(readyMemberList.size()));
            increaseMemberMoneySimulator(DECREASE_API_ENDPOINT, amount, decreaseTargetMembershipId);


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }

    private static void registerAccountSimulator(String apiEndPoint, int targetMembershipId) {
        try {
            URL url = new URL(apiEndPoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            Random random = new Random();

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("bankAccountNumber", generateRandomAccountNubmer());
            jsonRequestBody.put("bankName", BANK_NAME[random.nextInt(BANK_NAME.length)]);
            jsonRequestBody.put("membershipId", targetMembershipId);
            jsonRequestBody.put("valid", true);

            System.out.println("registerAccountSimulator : " + targetMembershipId);
            call(apiEndPoint, conn, jsonRequestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createMemberMoneySimulator(String apiEndPoint, int targetMembershipId) {
        try {
            URL url = new URL(apiEndPoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("membershipId", targetMembershipId);

            System.out.println("createMemberMoneySimulator : " + targetMembershipId);
            call(apiEndPoint, conn, jsonRequestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void increaseMemberMoneySimulator(String apiEndPoint, int amount, int targetMembershipId) {
        try {
            URL url = new URL(apiEndPoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("changingMoneyAmount", amount);
            jsonRequestBody.put("targetMembershipId", targetMembershipId);

            System.out.println("increaseMemberMoneySimulator : " + targetMembershipId);
            call(apiEndPoint, conn, jsonRequestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String generateRandomAccountNubmer() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }

    private static void call(String apiEndpoint, HttpURLConnection conn, JSONObject jsonRequestBody) throws IOException {
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(jsonRequestBody.toString().getBytes());
        outputStream.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        System.out.println("API Response from " + apiEndpoint + ": " + response.toString());
    }
}
