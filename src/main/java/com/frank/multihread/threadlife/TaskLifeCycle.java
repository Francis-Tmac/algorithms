package com.frank.multihread.threadlife;

/**
 * {@link  }
 *
 * @Date 2021/4/29
 * @Author frank
 * @Description:
 */
public interface TaskLifeCycle<T> {

    // 任务启东市会触发 onStart 方法
    void onStart(Thread thread);

    // 任务正在运行会触发 onRunning 方法
    void onRunning(Thread thread);

    // 任务运行结束时会触发 onFinish 方法，其中Result 是任务执行结束的结果
    void onFinish(Thread thread, T result);

    // 任务执行报错时会触发 onError 方法
    void onError(Thread thread, Exception e);


    class EmptyLifeCycle<T> implements TaskLifeCycle<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }

}
