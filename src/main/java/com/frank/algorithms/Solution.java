package com.frank.algorithms;

/**
 * @author fukangyang
 * @date 2022/6/10
 * @ desc
 */

public class Solution {

    public static int hammingWeight(int n) {

        int count = 0;
        while (n != 0){
            if((n & 1) == 1){
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    public int maxDeepLength = 0;

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftMax = maxDepth(root.left) + 1;
        int rightMax = maxDepth(root.right) + 1;
        return Math.max(leftMax, rightMax);
    }

    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int length = arr.length;
        int start = 0, end = 0;

        for(int i = 0 ; i < length; i++){
            int len1 = expandAlg(s, i, i);
            int len2 = expandAlg(s, i, i + 1);
            int len = Math.max(len1, len2);
            if(len > (end - start)){
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAlg(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }


    public static void main(String[] args) {
        System.out.println(hammingWeight(-1));
    }

}
