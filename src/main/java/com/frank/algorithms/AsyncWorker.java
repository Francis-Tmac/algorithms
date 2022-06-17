package com.frank.algorithms;

import com.frank.multihread.threadpool.ThreadPool;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author fukangyang
 * @date 2022/6/15
 * Q3: 并发任务控制器
 * @ desc
 */

public class AsyncWorker {


    private int capacity;

    // 定义任务队列
    private final LinkedList<Callable> runnableList = new LinkedList<>();

    /**
     * 构造函数
     *
     * @param capacity 最大并发数量
     */
    public AsyncWorker(int capacity) {
        // show me your code
        this.capacity = capacity;
    }

    /**
     * 任务提交函数: 当前正在执行的任务数小于 capacity 时, 立即异步执行, 否则
     * 等到任意一个任务执行完成后立即执行.
     *
     * @param task 任务函数
     * @param <T>  返回值类型
     * @return 返回由 Future 包装的任务函数的返回值, 其状态应该和 task 的执行结果一致
     */
    public <T> Future<T> submit(Callable<T> task) {
        // show me your code


        return null;
    }

}
