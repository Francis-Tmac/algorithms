package com.frank.algorithms.great;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2021-01-03
 **/

public class Singleton {

    private byte[] data = new byte[1024];

    private Singleton(){
        System.out.println("init singleton");
    }
    private static class Holder{
        private static Singleton singleton = new Singleton();
    }
    public static Singleton getInstance(){
        System.out.println("被调用getInstance（）");
        return Holder.singleton;
    }
}
