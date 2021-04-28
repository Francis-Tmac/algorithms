package com.frank.multihread.thread;

import java.util.Random;

/**
 * {@link  }
 *
 * @Date 2021/4/27
 * @Author frank
 * @Description:
 */
public class GlobalConstants {
    static{
        System.out.println("the GlobalConstants will be initialized.");
    }
    // 在其他类中使用MAX 不会导致 GlobalConstants 的初始化，静态代码块不会输出
    public final static int MAX = 100;

    // 此静态常量，使用 new 方法导致此类也被初始化
    public final static Child child = new Child();
}
