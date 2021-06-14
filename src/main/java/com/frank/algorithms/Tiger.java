package com.frank.algorithms;

/**
 * {@link  }
 *
 * @Date 2021/6/8
 * @Author frank
 * @Description:
 */
public class Tiger {

    class Node {
        int val;
        Node next;
    }
    private Node reverse(Node head){
        Node pre = head;
        if(head.next == null){
            return head;
        }
        Node cur = head.next;
        Node next = null;
        if(cur.next != null){
            next = cur.next;
        }
        head = null;
        while(cur != null){
            cur.next = pre;
            pre = cur;
            cur = next;
            // nullPointException
            if(next.next != null){
                next = next.next;
            }
        }
        return pre;
    }

    private Node headReverse(Node head){
        // 头插法的链表的头结点
        Node reverseHead = null;
        // 每次做头插法的节点
        Node temp = null;
        while(head.next != null){
            temp = head;
            head = temp.next;
            temp.next = reverseHead;
            reverseHead = temp;
        }

        return reverseHead;

    }

}
