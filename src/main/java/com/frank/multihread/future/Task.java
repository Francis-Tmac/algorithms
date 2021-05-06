package com.frank.multihread.future;

/**
 * {@link  }
 *
 * @Date 2021/5/1
 * @Author frank
 * @Description:
 */
@FunctionalInterface
public interface Task<IN, OUT> {

    OUT get(IN input);
}
