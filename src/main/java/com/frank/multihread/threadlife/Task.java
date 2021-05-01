package com.frank.multihread.threadlife;

/**
 * {@link  }
 *
 * @Date 2021/4/29
 * @Author frank
 * @Description:
 */
@FunctionalInterface
public interface Task<T> {

    // 任务执行接口，该接口允许有返回值
    T call();
}
