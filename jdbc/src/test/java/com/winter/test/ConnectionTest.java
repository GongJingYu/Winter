package com.winter.test;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionTest {

    public Connection get(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/winter";
            connection = DriverManager.getConnection(url, "root", "Oracle12");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Test
    public void testDataBaseMetaData() throws Exception{
        Connection connection = get();
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("metaData.getConnection() = " + (metaData.getConnection() == connection));
        
    }

    @Test
    public void testConnection() throws Exception{
        Connection connection = get();
//        connection.abort();
        System.out.println("connection.getCatalog() = " + connection.getCatalog());
        System.out.println("connection.getClientInfo() = " + connection.getClientInfo());
        System.out.println("connection.getMetaData() = " + connection.getMetaData());
        System.out.println("connection.getNetworkTimeout() = " + connection.getNetworkTimeout());
        System.out.println("connection.getSchema() = " + connection.getSchema());
        System.out.println("connection.getTypeMap() = " + connection.getTypeMap());
    }

    @Test
    public void test1() throws SQLException, IOException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/jdbc";
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pp = new Properties();
        pp.load(is);
        Connection connect = driver.connect(url, pp);
        System.out.println("connect = " + connect);
    }

    @Test
    public void test2() throws Exception{
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/jdbc";
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pp = new Properties();
        pp.load(is);
        Connection connect = driver.connect(url, pp);
        System.out.println("connect = " + connect);
    }

    @Test
    public void test3() throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        InputStream is = new FileInputStream("D:\\JavaCode\\数据库\\学习-数据库\\JDBC\\resource\\jdbc.properties");
        Properties pp = new Properties();
        pp.load(is);
        Connection connection = DriverManager.getConnection(url, pp);
        System.out.println("connection = " + connection);
    }

    /*
    * 好处
    * 1：实现了数据与代码的分离、解耦
    * 2：如果要修改配置文件信息，可以避免重新打包
    * */
    @Test
    public void test4() throws IOException, SQLException {
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        Properties pp = new Properties();
        pp.load(is);
        Connection conn = DriverManager.getConnection(pp.getProperty("url"), pp.getProperty("user"), pp.getProperty("password"));
        System.out.println("conn = " + conn);
    }
}
