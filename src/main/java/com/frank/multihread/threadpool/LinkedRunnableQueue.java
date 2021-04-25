package com.frank.multihread.threadpool;

import java.util.LinkedList;

/**
 * {@link  }
 *
 * @Date 2021/4/25
 * @Author frank
 * @Description:
 */
public class LinkedRunnableQueue implements RunnableQueue {

    // 任务队列的最大容量，在
    private final int limit;

    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy,
            ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList){
            if(runnableList.size() >= limit){
                denyPolicy.reject(runnable,threadPool);
            } else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take(){
        synchronized (runnableList){
            while (runnableList.isEmpty()){
                try {
                    runnableList.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        // 从任务队列头部移除一个任务
        return runnableList.removeFirst();
    }

    @Override
    public int size() {
        synchronized (runnableList){
            return runnableList.size();
        }
    }
}
