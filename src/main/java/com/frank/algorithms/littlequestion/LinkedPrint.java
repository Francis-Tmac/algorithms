package com.frank.algorithms.littlequestion;

import java.util.concurrent.locks.LockSupport;

/**
 * {@link  }
 *
 * @Date 2021/2/26
 * @Author frank.yang
 * @Description:
 */
public class LinkedPrint {

    private Node head;

    private Node tail;

    class Node {

        volatile int count;

        Thread thread;

        Node nextNode;

        public Node(int count, Node nextNode, String code) {
            this.count = count;
            this.nextNode = nextNode;
            this.thread = getThread(code);
        }

        public Node(int count, String code) {
            this.count = count;
            this.thread = getThread(code);
        }

        public void reduce() {
            count--;
        }
    }

    public Thread getThread(String code) {
        return new Thread(() -> {
            for (; ; ) {
                if (Thread.currentThread() != head.thread) {
                    LockSupport.park();
                } else {
                    try {
                        System.out.println(code);
                        Thread.sleep(500);
                        Node temp = head;
                        tail.nextNode = temp;
                        tail = tail.nextNode;
                        head = tail.nextNode;
                        temp.reduce();
                        LockSupport.unpark(head.thread);
                        if (temp.count < 1) {
                            break;
                        }
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    public void run() {
        Node D_Node = new Node(3, "D");
        Node C_Node = new Node(3, D_Node, "C");
        Node B_Node = new Node(3, C_Node, "B");
        Node A_Node = new Node(3, B_Node, "A");

        this.head = A_Node;
        this.tail = D_Node;

        B_Node.thread.start();
        C_Node.thread.start();
        D_Node.thread.start();
        A_Node.thread.start();
    }

    public static void main(String[] args) {
        LinkedPrint linkedPrint = new LinkedPrint();
        linkedPrint.run();
    }

}
