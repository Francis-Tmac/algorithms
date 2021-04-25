package com.frank.multihread.threadpool;

/**
 * {@link  }
 *
 * @Date 2021/4/25
 * @Author frank
 * @Description:
 */
public interface ThreadPool {

    // 提交任务到线程池
    void execute(Runnable runnable);

    // 关闭线程池
    void shutDown();

    // 获取线程池的初始化大小
    int getInitSize();

    // 获取线程池最大的线程数
    int getMaxSize();

    // 获取线程池的核心线程数量
    int getCoreSize();

    // 获取线程池中用于缓存任务队列的大小
    int getQueueSize();

    // 获取线程池中活跃的线程数量
    int getActiveCount();

    // 查看线程池是否已经被 shutDown
    boolean isShutDown();


}
