package com.winter.jdbc;

import com.winter.util.JDBConnection;

import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Query extends JDBConnection {

    public <T> List<T> query(Class<T> clazz, String sql){
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<T> list = new ArrayList<>();
            while (rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnName = rsmd.getColumnName(i + 1);
                    Field field = t.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return null;
    }

    public <T> T query(Class<T> clazz,String sql,Object...args){
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnName = rsmd.getColumnName(i + 1);
                    Field field = t.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return null;
    }
}
