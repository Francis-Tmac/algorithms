package com.frank.multihread.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link  }
 *
 * @Date 2021/4/28
 * @Author frank
 * @Description:
 */
public class ConditionCirclePrint {

    private static Lock lock = new ReentrantLock();

    private static Condition condition_a = lock.newCondition();

    private static Condition condition_b = lock.newCondition();

    private static Condition condition_c = lock.newCondition();

    public static void main(String[] args) {
        ConditionCirclePrint circlePrint = new ConditionCirclePrint();
        Thread a = new Thread(()->{
            circlePrint.runA(3);
        });
        Thread b = new Thread(()->{
            circlePrint.runB(3);
        });
        Thread c = new Thread(()->{
            circlePrint.runC(3);
        });
        a.start();
        b.start();
        c.start();
    }

    private void runA(int count) {

        for (; ; ) {
            lock.lock();
            try {
                System.out.println("A");
                condition_b.signal();
                count--;
                if (count <= 0) {
                    System.out.println("ready break a");
                    break;
                }
                condition_a.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void runB(int count){
        for (; ; ) {
            lock.lock();
            try {
                System.out.println("B");
                condition_c.signal();
                count--;
                if (count <= 0) {
                    System.out.println("ready break b");
                    break;
                }
                condition_b.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    private void runC(int count){
        for (; ; ) {
            lock.lock();
            try {
                System.out.println("C");
                condition_a.signal();
                count--;
                if (count <= 0) {
                    System.out.println("ready break c");
                    break;
                }
                condition_c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
