package com.berkanaslan;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=true&serverTimezone=UTC";
        String username = "hbstudent";
        String pass = "hbstudent";

        try {
            Connection myConn = DriverManager.getConnection(url, username, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
