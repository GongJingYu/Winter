package com.winter.test;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.winter.bean.Goods;
import com.winter.bean.User;
import com.winter.util.BaseSQL;
import org.junit.Test;

import java.sql.*;
import java.util.UUID;

public class JdbcTest {

    public Connection get(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/winter?rewriteBatchedStatements=true";
            connection = DriverManager.getConnection(url, "root", "Oracle12");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Test
    public void testBatch() throws Exception{
        long start = System.currentTimeMillis();
        Connection connection = get();
        String sql = "insert into user values(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < 100432; i++) {
            String s = UUID.randomUUID().toString().substring(0, 5);
            ps.setInt(1,i);
            ps.setString(2,s);
            ps.addBatch();
            if (i % 500 == 0){
                ps.executeBatch();

                ps.clearBatch();
            }

        }
        long end = System.currentTimeMillis();

        System.out.println("(end - start) = " + (end - start));
    }

    @Test
    public void testCommit() {
        Connection connection = get();
        String sql = "insert into user values(?,?)";
        try {
            BaseSQL baseSQL = new BaseSQL();
            baseSQL.setConnection(connection);
            connection.setAutoCommit(false);
            baseSQL.update(sql,4,"fbb");
            int i = 10 / 0;
            connection.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSelect() throws Exception{
        Connection connection = get();
        String sql = "select id,name from user";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setInt(1,1);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            User user = new User();
//            user.setId(rs.getInt("id"));
//            user.setName(rs.getString("name"));
//            System.out.println(user);
//        }
        BaseSQL baseSQL = new BaseSQL();
        baseSQL.setConnection(connection);
        baseSQL.selectList(sql,User.class).forEach(System.out::println);
    }

    @Test
    public void testInsert() throws Exception{
        Connection connection = get();
        String sql = "insert into user values(?,?)";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setInt(1,1);
//        ps.setString(2,"宫静雨");
//        ps.executeUpdate();

        BaseSQL baseSQL = new BaseSQL();
        baseSQL.setConnection(connection);
//        int result = baseSQL.update(sql,2,"yq");
//        int result = baseSQL.update("insert into goods values(?,?,?,?)",1,"华为手机","2999","100");
//        System.out.println("result = " + result);
        int result = baseSQL.update(sql,  3, "ccx");
        System.out.println("result = " + result);
    }

    @Test
    public void test1() throws Exception{
        Connection connection = get();
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("metaData.getDriverName() = " + metaData.getDriverName());
        ConnectionImpl impl = (ConnectionImpl) connection;
        System.out.println("impl.getHost() = " + impl.getHost());
    }

    @Test
    public void test2() throws Exception{
        Connection connection = get();
        System.out.println("connection.getTransactionIsolation() = " + connection.getTransactionIsolation());
    }
}
