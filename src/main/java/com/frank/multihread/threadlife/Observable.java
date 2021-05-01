package com.frank.multihread.threadlife;

/**
 * {@link  }
 *
 * @Date 2021/4/29
 * @Author frank
 * @Description:
 */
public interface Observable {

    // 任务生命周期的枚举类型
    enum Cycle{
        STARTED, RUNNING, DONE, ERROR
    }

    // 获取当前任务的生命周期
    Cycle getCycle();

    // 定义启动线程的方法，主要作用是为了屏蔽Thread 的其他方法
    void start();

    // 定义线程的打断方法，作用于 start 方法一样，也是为了屏蔽 Thread 的其他方法
    void interrupt();
}
