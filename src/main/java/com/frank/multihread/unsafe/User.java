package com.frank.multihread.unsafe;

/**
 * {@link  }
 *
 * @Date 2021/5/2
 * @Author frank
 * @Description:
 */
public class User {

    public User() {
        System.out.println("user 构造方法被调用");
    }

    private String name;

    private int age;

    private static String id = "USER_ID";

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +'\'' +
                ", id=" + id +'\'' +
                '}';
    }
}
