package com.frank.multihread;

import static java.lang.Thread.currentThread;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * {@link  }
 *
 * @Date 2021/5/1
 * @Author frank
 * @Description:
 */
public class ThreadLocalExample {

    public static void main(String[] args) {
        // 创建一个 ThreadLocal 实例
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


        IntStream.range(0,10).forEach(i -> new Thread(() -> {
            try{
                threadLocal.set(i);
                System.out.println(currentThread() + " set i " + threadLocal.get());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(currentThread() + " get i " + threadLocal.get());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start());

    }
}
