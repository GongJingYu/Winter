package com.winter.util;

import javax.lang.model.element.VariableElement;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BaseSQL {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public <T> T selectOne(String sql, Class<T> clazz, Object...args) throws Exception{
        List<T> list = selectList(sql, clazz, args);
        if (list.size() != 1)
            throw new IllegalArgumentException("查询单条数据，但返回多条 list.size() = " + list.size());
        return list.get(0);
    }

    public <T> List<T> selectList(String sql, Class<T> clazz, Object...args) throws Exception{
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        ResultSet rs = ps.executeQuery();
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
    }

    public int update(String sql, Object...args) throws Exception{
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        return ps.executeUpdate();
    }
}
