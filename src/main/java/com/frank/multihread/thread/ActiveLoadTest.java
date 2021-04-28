package com.frank.multihread.thread;

/**
 * {@link  }
 *
 * @Date 2021/4/27
 * @Author frank
 * @Description:
 */
public class ActiveLoadTest {
    public static void main(String[] args) {
        System.out.println("begin exe main");
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
