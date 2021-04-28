package com.frank.multihread.thread;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link  }
 *
 * @Date 2021/4/23
 * @Author frank
 * @Description:
 */
public  class Task implements Runnable {

    private static  final Object MUTEX = new Object();

    static List<Thread> waitList = new ArrayList<>();

    @Override
    public void run() {
        System.out.println("before sync execute :" + Thread.currentThread().getName());
        synchronized (Thread.currentThread()) {
            System.out.println("execute :" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
                waitList.add(Thread.currentThread());
                LockSupport.park(Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        try {
//            Thread.currentThread().wait();
//        }catch (Exception e){
//
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Thread t = new Thread(new Task());
            t.start();
        }
        Lock lock = new ReentrantLock();
        Condition c = lock.newCondition();
        TimeUnit.SECONDS.sleep(3);
        Thread t = waitList.remove(0);
        System.out.println("dddddddddddd");
        LockSupport.unpark(t);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        executorService.shutdown();
        executorService.execute(new Task());



    }
}
