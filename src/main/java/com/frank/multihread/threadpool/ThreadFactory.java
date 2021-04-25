package com.frank.multihread.threadpool;

/**
 * {@link  }
 *
 * @Date 2021/4/25
 * @Author frank
 * @Description:
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
