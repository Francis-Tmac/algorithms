package com.frank.multihread.readwritelock;

/**
 * {@link  }
 *
 * @Date 2021/4/30
 * @Author frank
 * @Description:
 */
public interface Lock {

    // 获取显示锁，没有获得锁的线程将会被阻塞
    void lock() throws InterruptedException;

    // 释放获取的锁
    void unlock();
}
