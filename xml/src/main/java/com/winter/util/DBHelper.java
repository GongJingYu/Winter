package com.winter.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBHelper {

    private DBHelper() {
    }

    private static class DB {
        private static final DBHelper DB_HELPER = new DBHelper();
    }

    public static DBHelper getInstance(){
        return DB.DB_HELPER;
    }

    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;

    private static String configFile;

    public Connection connection;
    public PreparedStatement preparedStatement;
    public ResultSet resultSet;

    static {
        InputStream is = DBHelper.class.getClassLoader().getResourceAsStream(configFile);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverClassName = properties.getProperty("driverClassName");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public static void setConfigFile(String configFile) {
        DBHelper.configFile = configFile;
    }

    public Connection getConnection(){
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert connection != null;
        return connection;
    }

    public void close(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs){
        rs = resultSet;
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
