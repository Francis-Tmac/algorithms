package com.frank.multihread.thread;

/**
 * {@link  }
 *
 * @Date 2021/4/27
 * @Author frank
 * @Description:
 */
public class Child extends Parent{
    static{
        System.out.println("the child will be initialized");
    }
    public static int x = 10;
}
