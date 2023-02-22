package com.liuwei.java;

import org.junit.Test;

import java.io.File;

/**
 * @author MikeCoder
 * @create 2022-10-3115:40
 */
public class User {
    private int id;
    private int age;
    private String name;

    public User() {
    }

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Test
    public void test() {
        User user = new User();
        File file = new File("D:\\360MoveData\\Users\\大树王子\\Desktop\\财务管理");
        boolean mkdir = file.mkdir();
        if (mkdir) {
            System.out.println("创建成功");
        }
        user.For(file);
    }

    public void For(File f) {
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                For(files[i]);
            } else {
                System.out.println(files[i].getName());
            }

        }
    }

}
