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

    public String longestPalindrome(String s) {
        int length = s.length();
        int start = 0, end = 0;
        for(int i = 0 ; i < length; i++){
            int len1 = extendAlg(s, i, i);
            int len2 = extendAlg(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len > (end - start)){
                end = i + len/2;
                start = i - (len - 1)/2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int extendAlg(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            right--;
            left++;
        }
        return right - left -1;
    }

    public int maxSubArray(int[] nums) {
        int curMax = nums[0], resMax = nums[0];
        for(int i = 1; i < nums.length; i++){
            curMax = Math.max(curMax + nums[i], nums[i]);
            resMax = Math.max(curMax, resMax);
        }
        return resMax;
    }


}
