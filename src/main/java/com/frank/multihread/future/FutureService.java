package com.frank.multihread.future;


/**
 * {@link  }
 *
 * @Date 2021/5/1
 * @Author frank
 * @Description:
 */
public interface FutureService<IN, OUT>{

    Future<?> submit(Runnable runnable);

    Future<OUT> submit(Task<IN, OUT> task, IN input);

    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);

    static <T,R> FutureService<T, R> newService(){
        return new FutureServiceImpl<>();
    }
}
