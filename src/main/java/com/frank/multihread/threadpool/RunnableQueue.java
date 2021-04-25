package com.frank.multihread.threadpool;

/**
 * {@link  }
 *
 * @Date 2021/4/25
 * @Author frank
 * @Description:
 */
public interface RunnableQueue {

    // 当有新的任务进来时会先 offer 到队列中
    void offer(Runnable runnable);

    // 工作线程通过take 获取 Runnable
    Runnable take();

    //  获取任务队列中的数量
    int size();
}
