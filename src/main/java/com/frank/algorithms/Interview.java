package com.frank.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * {@link  }
 *
 * @Date 2021/3/8
 * @Author frank.yang
 * @Description:
 */
public class Interview {

    private static boolean is(String ss) {
        if (ss.isEmpty()){
            return false;
        }
        char[] chars = ss.toCharArray();
        if (chars.length == 1){
            return true;
        }
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }


    private static int[] getTarget(int[] array, int target) {
        int[] arr = new int[]{-1};
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return arr;
    }

    static class TreeNode {

        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /***
     * 二叉树的层序遍历
     ***/
    private static List<List<Integer>> levelSort(TreeNode node) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            boolean flag = false;
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.peek();
                queue.poll();
                if (cur == null) {
                    level.add(null);
                    continue;
                }
                flag = true;
                level.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if (flag && !level.isEmpty()) {
                result.add(level);
            }
        }
        return result;
    }

//    public static void main(String[] args) {
//        int[] b = new int[]{1,2,3,4,5,6,7};
//
//        int[] arr = twoSum(b,13);
//        System.out.println(arr);

//        TreeNode node_4 = new TreeNode(4);
//        TreeNode node_3 = new TreeNode(3, node_4, null);
//        TreeNode node_2 = new TreeNode(2);
//        TreeNode node_1 = new TreeNode(1, node_2, node_3);
//
//        List<List<Integer>> list = levelSort(node_1);
//
//        list.get(0);


//        int[] a = new int[]{1,2,3};
//        int[] b = new int[]{4,5,6,7};
//        int[] c = mergeSort(a,b);
//        System.out.println(c[0]);

//        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,4,7));
//        List<Integer> list2 = new ArrayList<>(Arrays.asList(2,5,8,11));
//        List<Integer> list3 = new ArrayList<>(Arrays.asList(14,19,20,22));
//        List<Integer> list4 = new ArrayList<>(Arrays.asList(16,23,25,30,33));
//        List<Integer> list5 = new ArrayList<>(Arrays.asList(3,6,9,12,23));
//
//        List<List<Integer>> listList = new ArrayList<>(Arrays.asList(list1,list2,list3,list4,list5));
//        List<Integer> targetList = mergeList(listList).get(0);
//
//        System.out.println(targetList);

//    }

    private static List<List<Integer>> mergeList(List<List<Integer>> originList){
        int listSize = originList.size();
        if(listSize == 1){
            return originList;
        }
        List<List<Integer>> newList = new ArrayList<>();

        for (int i = 0; i < (listSize + 1) / 2; i++){
            if(i == listSize-1-i){
                newList.add(originList.get(i));
            }else{
                newList.add(mergeSort(originList.get(i),originList.get(listSize-1-i)));
            }
        }
        newList = mergeList(newList);
        return newList;
    }
    // 合并两个 链表
    private static List<Integer> mergeSort(List<Integer> arrA, List<Integer> arrB){
        List<Integer> array = new ArrayList<>();
        int size = arrA.size() + arrB.size();
        if(size == 0){
            return array;
        }
        int a = 0;
        int b = 0;
        for(int i = 0 ; i < size ; i++){
            if(a >= arrA.size()){
                array.add(i, arrB.get(b));
                b++;
                continue;
            }
            if(b >= arrB.size()){
                array.add(i, arrA.get(a));
                a++;
                continue;
            }
            if(arrA.get(a) < arrB.get(b)){
                array.add(i,arrA.get(a));
                a++;
            }else{
                array.add(i, arrB.get(b));
                b++;
            }
        }
        return array;
    }

    private static int[] mergeSort(int[] arrA, int[] arrB){
        int size = arrA.length + arrB.length;
        if(size == 0){
            return new int[]{0};
        }
        int[] array = new int[arrA.length + arrB.length];
        int a = 0;
        int b = 0;
        for(int i = 0 ; i < size ; i++){
            if(a >= arrA.length){
                array[i] = arrB[b];
                b++;
                continue;
            }
            if(b >= arrB.length){
                array[i] = arrA[a];
                a++;
                continue;
            }
            if(arrA[a] < arrB[b]){
                array[i] = arrA[a];
                a++;
            }else{
                array[i] = arrB[b];
                b++;
            }
        }
        return array;
    }

    public  static int[] twoSum2(int[] nums, int target) {
        HashMap map = new HashMap();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],i);
        }
        for(int i = 0; i < nums.length; i++){
            Integer j = target - nums[i];
            if(map.get(j) != null){
                if(i != (int)map.get(j)){
                    return new int[]{i,(int)map.get(j)};
                }
            }
        }
        return null;
    }

    public static int maxSubArray(int[] nums){
        int pre = 0, maxAns = nums[0];
        for(int x : nums){
            pre= Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static int sum(int x, int y){
        int  z = x | y;
        return z;
    }

    public static void main(String[] args) {
//        int[] b = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int max = maxSubArray(b);
//
//        System.out.println(max);

        int[] a= new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>>  b = threeSum(a);
        b.get(1);
//        System.out.println(sum(3,2));
//
//        int[] arr = twoSum(b, 13);
//        System.out.println(arr);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(hashMap.containsKey(target - nums[i])){
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums.length == 0 || nums.length == 1){
            return null;
        }
        Arrays.sort(nums);
        List<List<Integer>> targetList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                targetList.add(new ArrayList<>());
                return targetList;
            }
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int j = i + 1;
            int k = nums.length -1 ;
            int current = nums[i];
            while ( j < k){
                if(current + nums[j] + nums[k] == 0){
                    List<Integer> list = Arrays.asList(current, nums[j], nums[k]);
                    targetList.add(list);
                    while (j < k && nums[j] == nums[j+1]){
                        j++;
                    }
                    while (j < k && nums[k] == nums[k-1]){
                        k--;
                    }
                    k--;
                    j++;
                }else if(current + nums[j] + nums[k] > 0){
                    k--;
                }else {
                    j++;
                }
            }

        }
        return targetList;
    }

}
