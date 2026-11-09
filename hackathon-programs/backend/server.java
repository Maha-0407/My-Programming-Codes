package com.hackathon;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.sql.*;
import java.io.*;

public class Server {

    static final String URL =
        "jdbc:mysql://localhost:3306/student_attendance";

    static final String USER = "root";
    static final String PASSWORD = "YOUR_PASSWORD";

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8080), 0
        );

        // View students
        server.createContext("/students", exchange -> {

            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                "SELECT * FROM students"
            );

            String result = "[";

            while (rs.next()) {

                result += "{"
                    + "\"id\":" + rs.getInt("id") + ","
                    + "\"name\":\"" + rs.getString("name") + "\","
                    + "\"roll_no\":\"" + rs.getString("roll_no") + "\","
                    + "\"department\":\"" + rs.getString("department") + "\","
                    + "\"year\":" + rs.getInt("year")
                    + "},";
            }

            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }

            result += "]";

            exchange.getResponseHeaders().set(
                "Access-Control-Allow-Origin", "*"
            );

            exchange.getResponseHeaders().set(
                "Content-Type", "application/json"
            );

            byte[] data = result.getBytes();

            exchange.sendResponseHeaders(200, data.length);

            exchange.getResponseBody().write(data);

            exchange.close();
            con.close();
        });

        server.start();

        System.out.println("Server started successfully!");
        System.out.println("http://localhost:8080");
    }
}