package com.frank.algorithms.littlequestion;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-12-26
 **/

public class LinkedCirculationPrint {

    Node head ;

    Node tail ;

    private class Node{
        Thread thread;

        int count;

        Node nextNode;

        public Node(String code, int count) {
            this.thread = getThread(code);
            this.count = count;
        }

        public Node(String code, int count, Node nextNode) {
            this.thread = getThread(code);
            this.count = count;
            this.nextNode = nextNode;
        }

        public void decrease(){
            count--;
        }
    }

    public Thread getThread(String code){
        return new Thread(()->{
            for(;;) {
//                System.out.println("----判断----" + code);
                if (Thread.currentThread() != head.thread) {
                    LockSupport.park();
                } else {
                    System.out.println(code);
                    Node temp = head;

                    // 处理尾节点
                    tail.nextNode = head;
                    tail = tail.nextNode;

                    head = tail.nextNode;

                    LockSupport.unpark(head.thread);
                    if (temp.count <= 1) {
                        break;
                    }
                    temp.decrease();
                }
            }
        });
    }

    public void run(int count){
        Node CNode = new Node("C", count);
        Node BNode = new Node("B", count, CNode);
        Node ANode = new Node("A", count, BNode);
        head = ANode;
        tail = CNode;
        CNode.thread.start();
        BNode.thread.start();
        ANode.thread.start();

    }

    public static void main(String[] args) {
        LinkedCirculationPrint lcp = new LinkedCirculationPrint();
        lcp.run(5);
    }
}
