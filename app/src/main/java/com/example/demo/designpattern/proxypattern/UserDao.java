package com.example.demo.designpattern.proxypattern;

public class UserDao implements IUserDao {
    //目标对象类
    @Override
    public void save() {
        System.out.println("UserDao --------已经保存数据----------");
    }

    @Override
    public void delete() {
        System.out.println("UserDao --------已经删除数据----------");
    }

    @Override
    public void insert() {
        System.out.println("UserDao --------已经插入数据----------");
    }
}
