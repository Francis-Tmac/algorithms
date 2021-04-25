package com.frank.multihread.thread;

import java.util.concurrent.TimeUnit;

/**
 * {@link  }
 *
 * @Date 2021/4/23
 * @Author frank
 * @Description:
 */
public  class Task implements Runnable {

    private static  final Object MUTEX = new Object();

    @Override
    public void run() {
        System.out.println("before sync execute :" + Thread.currentThread().getName());
        synchronized (MUTEX) {
            System.out.println("execute :" + Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Task()).start();
        }

    }

}
