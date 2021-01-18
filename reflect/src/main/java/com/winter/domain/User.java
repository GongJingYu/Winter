package com.winter.domain;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author
 * @version
 * @see
 * @since
 * @param
 * @return
 * @exception
 *
 */
public class User extends ArrayList implements List, Serializable,Comparable, Comparator {
    private static final long serialVersionUID = 4321324321L;

    Object readResolve() throws ObjectStreamException {
        return this;
    }

    private Integer id;
    public String name;

    public void a(String n,Integer a){
        System.out.println(n + "  " + a);
    }

    public User() {
    }

    private User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
