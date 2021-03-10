package com.frank.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-12-06
 **/

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[] arr = {5,7,7,8,8,10};
//        int[] target = s.searchRange(arr, 8);
//        for (int a : target){
//            System.out.println(a);
//        }

        int[] arr = {1,0,3,0,5,6};
        int a = arr.length;
//        System.out.println(s.searchInsert(arr, 7));
        String str = "abdncnd";
        char[] chars = str.toCharArray();
        int[] charArr = new int[256];
        charArr[chars[1]] = 1;
        System.out.println();
//        s.moveZeroes(arr);
    }

    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if( len == 0){
            return new int[]{-1,-1};
        }
        int fistLocation = findFistLocation(nums, target);
        if(fistLocation == -1){
            return new int[]{-1,-1};
        }
        int lastLocation = findLastLocation(nums, target);
        return new int[]{fistLocation, lastLocation};
    }

    private int findFistLocation(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        //当 left >= right 时结束循环
        while (left < right){
            int mid = ((right-left) >> 1)+ left;
            if(target > nums[mid]){
                // 在 [mid + 1, right] 中查找
                left = mid + 1;
            } else if(target == nums[mid]){
                // 在[left, mid] 中查找
                right = mid;
            } else {
                // target < nums[mid] 时
                // 在 [left, mid - 1] 中查找
                right = mid - 1;
            }
        }
        if (nums[left] == target){
            return left;
        }else {
            return -1;
        }

    }

    private int findLastLocation(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;

        //当 left >= right 时结束循环
        while (left < right){
            int mid = ((right - left + 1) >> 1) + left;
            if(target > nums[mid]){
                // 在 [mid + 1, right] 中查找
                left = mid + 1;
            } else if(target == nums[mid]){
                // 在[mid, right] 中查找
                left = mid;
            } else {
                // target < nums[mid] 时
                // 在 [left, mid - 1] 中查找
                right = mid - 1;
            }
        }
        if (nums[left] == target){
            return left;
        }else {
            return -1;
        }
    }

    public int searchInsert(int[] nums, int target) {
        int left  = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = ((right - left) >> 1) + left;
            if(target == nums[mid]){
                return mid;
            } else if(target > nums[mid]){
                left = mid + 1;

            }else {
                right = mid - 1;
            }
        }
        return left;

    }

    public void moveZeroes(int[] nums) {
        Queue<Integer> normalQueue = new LinkedList();
        Queue<Integer> zeroQueue = new LinkedList();
        for (int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                zeroQueue.add(0);
            }else{
                normalQueue.add(nums[i]);
            }
        }
        for (int i = 0; i< nums.length; i++){
            if (!normalQueue.isEmpty()){
                nums[i] =  normalQueue.poll();
                continue;
            }
            if (!zeroQueue.isEmpty()){
                nums[i] = zeroQueue.poll();
                continue;
            }
        }
    }
    public void moveZeroes1(int[] nums) {
        int index = 0;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for (int j = index; j < nums.length; j++){
            nums[j] = 0;
        }
    }
}