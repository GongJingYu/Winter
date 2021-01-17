package com.winter.jdbc;

import java.util.List;

public interface DBHandler<T> {

    /**
     * 传入查询所需要的参数，返回一个 T 的对象
     * @param args 查询所需要的参数
     * @return 返回类型为 T
     */
    T select(Object...args);

    /**
     *
     * @return 返回一个 T 类型的集合
     */
    List<T> selectAll();

    boolean insert(T t);

    boolean update(T t);

    boolean delete(Object...args);
}
