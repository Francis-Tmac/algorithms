package com.frank.algorithms.littlequestion;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-12-25
 **/

public class CirculationPrint {

    public static void main(String[] args) {
        Circulation circulation = new Circulation();
        new Thread(()->{
            circulation.printA(3);
        }).start();
        new Thread(()->{
            circulation.printB(3);
        }).start();
        new Thread(()->{
            circulation.printC(3);
        }).start();
    }
}

class Circulation{
    Lock lock = new ReentrantLock();

    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    /***
     * 完全可以不适用 flag 做状态控制
     */
    volatile int flag = 1;

    public void printA(int count){
        lock.lock();
        try{
            while (true){
                if(flag != 1){
                    conditionA.await();
                }
                System.out.println("A");
                TimeUnit.SECONDS.sleep(1);
                flag = 2;

                conditionB.signal();
                if(count <= 1){
                    break;
                }
                count--;
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public void printB(int count){
        lock.lock();
        try{
            while (true){
                if(flag != 2){
                    conditionB.await();
                }
                System.out.println("B");
                TimeUnit.SECONDS.sleep(1);
                flag = 3;

                conditionC.signal();
                if(count <= 1){
                    break;
                }
                count--;
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public void printC(int count){
        lock.lock();
        try{
            while (true){
                if(flag != 3){
                    conditionC.await();
                }
                System.out.println("C");
                TimeUnit.SECONDS.sleep(1);
                flag = 1;

                conditionA.signal();
                if(count <= 1){
                    break;
                }
                count--;
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
}