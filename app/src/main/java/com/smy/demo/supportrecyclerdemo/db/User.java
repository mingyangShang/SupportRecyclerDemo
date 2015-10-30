package com.smy.demo.supportrecyclerdemo.db;

/**
 * Created by starkshang on 2015/10/29.
 */
public class User {
    public long id;
    public String name;
    public int age;

    public User(String name,int age){
        this.id = -1L;
        this.name = name;
        this.age = age;
    }

}
