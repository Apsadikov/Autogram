package com.autogram.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection instance;

    private DBConnection() {
    }

    public static Connection getInstance() {
        if (instance == null) {
            try {
                InputStream file = new FileInputStream("");
                Properties properties = new Properties();
                properties.load(file);
                instance = DriverManager.getConnection("jdbc:mysql://" +
                        properties.getProperty("ip") + ":" + Integer.parseInt(properties.getProperty("port")) + "/" +
                        properties.getProperty("db_name") + "?" +
                        "user=" + properties.getProperty("user") + "&" +
                        "password=" + properties.getProperty("password") + "&" +
                        "useUnicode=true&" +
                        "serverTimezone=UTC");
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("Failed to connect to database");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
