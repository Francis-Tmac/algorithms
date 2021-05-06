package com.frank.multihread.future;

/**
 * {@link  }
 *
 * @Date 2021/5/1
 * @Author frank
 * @Description:
 */
@FunctionalInterface
public interface Callback<T> {

    // 任务完成后会调用该方法，其中T 为任务执行后的结果
    void call(T t);
}
