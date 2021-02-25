package com.frank.algorithms;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * {@link  }
 *
 * @Date 2020/12/23
 * @Author frank.yang
 * @Description:
 */
public class LinkedPrintDemo {

    private volatile Node head;

    private volatile Node tail;

    private class Node {

        volatile int count;

        Thread thread;

        Node nextNode;

        public Node(String code, int count) {
            this.thread = getThread(code);
            this.count = count;
        }

        public Node(String code, Node node, int count) {
            this.thread = getThread(code);
            this.nextNode = node;
            this.count = count;
        }

        public void reduce() {
            count--;
        }
    }

    private Thread getThread(String code) {

        return new Thread(() -> {
            for (; ; ) {
                System.out.println("-----开始判断----" + code);
                if (Thread.currentThread() != head.thread) {
                    LockSupport.park(Thread.currentThread());
                } else {
                    try {
                        Node temp = head;
                        System.out.println(code);
                        TimeUnit.SECONDS.sleep(1);
                        // 将尾结点重置
                        tail.nextNode = head;
                        tail = tail.nextNode;
                        // 重置头节点
                        head = tail.nextNode;
                        //唤醒新的头节点
                        LockSupport.unpark(head.thread);
                        if (temp.count <= 1) {
                            break;
                        }
                        temp.reduce();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void run(int count) {

        Node DNode = new Node("D", count);
        Node CNode = new Node("C", DNode, count);
        Node BNode = new Node("B", CNode, count);
        Node ANode = new Node("A", BNode, count);

        tail = DNode;
        head = ANode;

        BNode.thread.start();
        ANode.thread.start();
        DNode.thread.start();
        CNode.thread.start();

    }

    public static void main(String[] args) {
        LinkedPrintDemo aqsDemo = new LinkedPrintDemo();
        aqsDemo.run(3);
    }
}
