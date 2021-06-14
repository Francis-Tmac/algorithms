package com.frank.designmode;

import com.frank.algorithms.great.Singleton;

/**
 * {@link  }
 *
 * @Date 2021/5/29
 * @Author frank
 * @Description:
 */
public class SingletonLazy {

    private static volatile SingletonLazy singleton;

    private SingletonLazy(){}

    public SingletonLazy getSingleton(){
        if(singleton == null){
            synchronized (SingletonLazy.class){
                if(singleton == null){
                    // 1.为singleton对象分配内存
                    // 2.给singleton对象初始化
                    // 3.将singleton 指针指向 singleton对象
                    // 重排序 132 时发生错误
                    singleton = new SingletonLazy();
                }
            }
        }
        return singleton;
    }
}
