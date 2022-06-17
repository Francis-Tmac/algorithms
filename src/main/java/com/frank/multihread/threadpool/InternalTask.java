package com.frank.multihread.threadpool;

/**
 * {@link  }
 *
 * @Date 2021/4/25
 * @Author frank
 * @Description:
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        // 如果当前任务为 running 并且没有被中断，这其将不断地从Queue 中获取 runnable ,然后执行run 方法
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (Exception e){
                running = false;
                break;
            }
        }
    }

    // 停止当前任务
    public void stop(){
        this.running = false;
    }
}
