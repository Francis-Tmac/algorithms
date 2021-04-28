package com.frank.multihread.thread;

/**
 * {@link  }
 *
 * @Date 2021/1/19
 * @Author frank.yang
 * @Description:
 */
public class Singleton {
    // 1
    private static Singleton instance = new Singleton();

    public static int x = 0;

    public static int y;

    // 2
//    private static Singleton instance = new Singleton();

    private Singleton(){
        System.out.println("construct Singleton");
        x++;
        y++;
    }

    public static Singleton getInstance(){
        System.out.println("static getInstance");
        return instance;
    }

}
