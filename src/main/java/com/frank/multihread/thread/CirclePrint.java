package com.frank.multihread.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * {@link  }
 *
 * @Date 2021/4/28
 * @Author frank
 * @Description:
 */
public class CirclePrint {

    private Node head;

    private Node tail;

    private Object MONITOR = new Object();

    private volatile AtomicInteger flag  = new AtomicInteger(1);

    public static void main(String[] args) {
        CirclePrint c = new CirclePrint();
        c.setLinked();
//        c.circleMonitor();
    }

    private void circleMonitor(){
        Thread thread_a = new Thread(()->{run("A",3,1, 3);});
        Thread thread_b = new Thread(()->{run("B",3,2, 3);});
        Thread thread_c = new Thread(()->{run("C",3,3, 3);});

        thread_a.start();
        thread_b.start();
        thread_c.start();

    }

    /***
     *
     ***/
    private void run(String value, int count, int cur_flag, int max){
        for(;;){
            synchronized (MONITOR){
                try {
                    if (flag.get() != cur_flag) {
                        MONITOR.wait();
                    }else {
                        System.out.println(value);
                        if (flag.getAndIncrement() == max) {
                            flag.set(1);
                        }
                        count--;
                        if (count <= 0) {
                            break;
                        }
                    }
                } catch (InterruptedException e){

                }finally {
                    MONITOR.notifyAll();
                }
            }
        }
    }

    /***
     * 维护一个双向链表
     */
    private void setLinked() {
        int count = 3;
        Node node_D = new Node("D", 5);
        Node node_C = new Node(node_D, "C", 2);
        Node node_B = new Node(node_C, "B", count);
        Node node_A = new Node(node_B, "A", 7);
        node_D.nextNode = node_A;

        node_B.preNode = node_A;
        node_C.preNode = node_B;
        node_D.preNode = node_C;
        node_A.preNode = node_D;
        this.tail = node_D;
        this.head = node_A;

        node_D.thread.start();
        node_C.thread.start();
        node_B.thread.start();
        node_A.thread.start();
    }

    /***
     * 只有头结点才可以打印
     * @param value
     * @return
     */
    private Thread getThread(String value) {
        return new Thread(() -> {
            for (; ; ) {
                if (Thread.currentThread() != head.thread) {
                    LockSupport.park(Thread.currentThread());
                }
                if (head.getCount() > 0) {
                    System.out.println(value);
                }
                Node curNode = head;
//                tail.nextNode = curNode;
//                tail = tail.nextNode;
//                head = curNode.nextNode;
                tail = curNode;
                head = curNode.nextNode;
                LockSupport.unpark(head.thread);
                if (curNode.decreaseCount() <= 0) {
                    if(curNode.preNode != null){
                        curNode.preNode.nextNode = curNode.nextNode;
                        curNode.nextNode.preNode = curNode.preNode;
                    }
                    break;
                }
            }
        });
    }

    private class Node {

        private Node preNode;

        private Node nextNode;

        private Thread thread;

        private int count;

        public Node(String value, int count) {
            this.nextNode = null;
            this.thread = getThread(value);
            this.count = count;
        }

        public Node(Node nextNode, String value, int count) {
            this.nextNode = nextNode;
            this.thread = getThread(value);
            this.count = count;
        }

        private int decreaseCount() {
            return count--;
        }

        private int getCount() {
            return count;
        }

    }
}


