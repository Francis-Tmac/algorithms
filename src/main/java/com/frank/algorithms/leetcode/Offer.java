package com.frank.algorithms.leetcode;

import java.util.LinkedList;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2021-05-22
 **/

public class Offer {

    private int[] arr;
    private int length;
    public int[] reversePrint(ListNode head) {
        if(head == null){
            return new int[0];
        }
        print(head, 0);
        return arr;
    }
    private int print(ListNode head, int i){
        if(head == null){
            arr = new int[i];
            length = i;
            return i;
        }
        int cur = print(head.next, i + 1);
        arr[length - cur] = head.val;
        return i;
    }

    private int getLength(ListNode head){
        if(head == null){
            return 0;
        }
        return getLength(head.next) + 1;
    }


    public ListNode reverseList(ListNode head) {
        if(head == null){
            return new ListNode(0);
        }
        if(head.next == null){
            return head;
        }
        ListNode cur = head.next, pre = head;
        pre.next = null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;

    }

    LinkedList<Integer> A,B;

    public void CQueue() {
        A = new LinkedList();
        B = new LinkedList();
    }

    public void appendTail(int value) {
        A.addLast(value);
    }

    public int deleteHead() {

        if(!B.isEmpty()){return B.removeLast();}

        if(A.isEmpty()) {return -1;}

        while (!A.isEmpty()){
            B.addLast(A.removeLast());
        }
        return B.removeLast();
    }





    public static void main(String[] args) {
        Offer offer = new Offer();

        ListNode listNode = offer.getListNode();
        ListNode reverse = offer.reverseList(listNode);

        System.out.println(reverse);

//        int length = offer.getLength(offer.getListNode());
//        System.out.println("length : " + length);
//        int[] arr = offer.reversePrint(offer.getListNode());
//        for (int i : arr){
//            System.out.println(i);
//        }
    }

    public ListNode getListNode(){
        ListNode a_node = new ListNode(4);
        ListNode b_node = new ListNode(3,a_node);
        ListNode c_node = new ListNode(2,b_node);
        ListNode d_node = new ListNode(1,c_node);
        return d_node;
    }

    class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

