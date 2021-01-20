package com.winter.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBConnection {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;

    static {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc2.properties");
        Properties pp = new Properties();
        try {
            pp.load(is);
            driver = pp.getProperty("driver");
            url = pp.getProperty("url");
            user = pp.getProperty("user");
            password = pp.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs){
        this.rs = rs;
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null){
            try {
                ps.close();
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
