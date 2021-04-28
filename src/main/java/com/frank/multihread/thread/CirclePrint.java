package com.frank.multihread.thread;

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

    public static void main(String[] args) {
        CirclePrint c = new CirclePrint();
        c.setList();

    }

    private void setList(){
        int count = 3;
        Node node_D = new Node("D", count);
        Node node_C = new Node(node_D,"C", 2);
        Node node_B = new Node(node_C,"B",count);
        Node node_A = new Node(node_B,"A",count);
        this.tail = node_D;
        this.head = node_A;
        node_D.thread.start();
        node_C.thread.start();
        node_B.thread.start();
        node_A.thread.start();
    }
    private Thread getThread(String value){
        return new Thread(()->{
            for(;;){
                if(Thread.currentThread() != head.thread){
                    LockSupport.park(Thread.currentThread());
                }
                if(head.getCount() > 0){
                    System.out.println(value);
                }
                Node curNode = head;
                tail.nextNode = curNode;
                tail = tail.nextNode;
                head = curNode.nextNode;
                LockSupport.unpark(head.thread);
                if(curNode.decreaseCount() <= 0){
                    break;
                }
            }
        });
    }

private class Node{

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
        private int decreaseCount(){
            return count--;
        }
        private int getCount(){
            return count;
        }

    }
}


