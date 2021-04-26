package com.frank.multihread.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link  }
 *
 * @Date 2021/4/25
 * @Author frank
 * @Description:
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException{
        final ThreadPool threadPool = new BasicThreadPool(2,6,4,1000);
        AtomicInteger j = new AtomicInteger(0);
        // 定义20 个 任务并且提交给线程池
        for (int i = 0; i < 10; i++) {
            threadPool.execute(()->{
                try{
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " is running and done. ------ " + j.getAndIncrement());
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        for(;;){
            System.out.println("getActiveCount: "+ threadPool.getActiveCount());
            System.out.println("getQueueSize: " + threadPool.getQueueSize());
            System.out.println("getCoreSize: " + threadPool.getCoreSize());
            System.out.println("getMaxSize: " + threadPool.getMaxSize());
            System.out.println("--------------------------------");
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
