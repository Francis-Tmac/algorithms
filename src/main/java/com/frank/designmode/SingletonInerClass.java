package com.frank.designmode;

/**
 * {@link  }
 *
 * @Date 2021/5/29
 * @Author frank
 * @Description:
 */
public class SingletonInerClass {

    private static class SingletonHolder{
        private static final SingletonInerClass INSTANCE = new SingletonInerClass();
    }

    private SingletonInerClass() { }

    public static final SingletonInerClass getInstance(){
        // class 对象的初始化锁 
        return SingletonHolder.INSTANCE;
    }
}
