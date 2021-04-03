package com.frank.algorithms.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import jodd.util.StringUtil;

/**
 * {@link  }
 *
 * @Date 2021/3/30
 * @Author frank.yang
 * @Description:
 */
public class LeetCode {

    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEndSinglePoint(ListNode head, int n) {

        int size = 0;
        ListNode curNode = head;
        if (head.next == null && n == 1) {
            return null;
        }
        while (curNode != null) {
            size++;
            curNode = curNode.next;
        }
        int target = size - n - 1;
        if (target == -1) {
            return head.next;
        }
        curNode = head;
        for (int i = 0; i < target; i++) {
            curNode = curNode.next;
        }
        if (n == 1) {
            curNode.next = null;
        } else {
            curNode.next = curNode.next.next;
        }

        return head;
    }

    public ListNode removeNthFromEndTwoPoint(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode before = pre, after = pre;
        while (n != 0) {
            after = after.next;
            n--;
        }
        // todo 判断是 next还是 current
        while (after.next != null) {
            after = after.next;
            before = before.next;
        }
        before.next = before.next.next;
        return pre.next;
    }

    public ListNode getListNode() {
        ListNode node_5 = new ListNode(5);
        ListNode node_4 = new ListNode(4, node_5);
        ListNode node_3 = new ListNode(3, node_4);
        ListNode node_2 = new ListNode(2, node_3);
        ListNode node_1 = new ListNode(1, node_2);
        return node_1;
    }
    // 75. 颜色分类

    /****
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
     * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * @param nums
     */
    public static void sortColors(int[] nums) {
        // 三路快速排序，用 1 作为 partition;
        // 定义 [0, i) 区间为 x < 1
        // [i,j) 区间 x = 1
        // [k,nums.length - 1] x > 1
        // 当 j = k 时结束
        int partition = 1;
        int i = 0, j = 0, k = nums.length;
        while (j < k) {
            if (nums[j] == partition) {
                j++;
            } else if (nums[j] < partition) {
                if (i != j) {
                    swap(i, j, nums);
                }
                j++;
                i++;
            } else {
                k--;
                swap(j, k, nums);
            }
        }
    }

    // 283. 移动零
    public static void moveZeroes(int[] nums) {
        // if(nums.length < 2){
        //     return ;
        // }
        // 维护两个指针
        // i 正常遍历
        // j 最接近开始的0 的位置
        // 4,2,4,0,0,3,0,5,1,0
        for (int i = 0, j = 0; i < nums.length && j < nums.length; i++) {
            if (i > j && nums[i] != 0 && nums[j] == 0) {
                swap(i, j, nums);
            }
            while (j < nums.length && nums[j] != 0) {
                j++;
            }
        }
    }

    private static void swap(int i, int j, int[] nums) {
        int k = nums[i];
        nums[i] = nums[j];
        nums[j] = k;
    }

    public static void main(String[] args) {
//        LeetCode leetCode = new LeetCode();
//        ListNode cur = leetCode.removeNthFromEndSinglePoint(leetCode.getListNode(),2);
//        ListNode cur = leetCode.removeNthFromEndTwoPoint(leetCode.getListNode(),2);
//        System.out.println(cur.val);

//        int[] b = new int[]{2,0,1};
        int[] b = new int[]{4, 2, 4, 0, 0, 3, 0, 5, 1, 0};
//        int[] b = new int[]{2,1};
//        moveZeroes(b);
//        System.out.println(b[0]);
        int[] arr = new int[]{1, 2, 3};
//        List<List<Integer>> list = permute(arr);
//        System.out.println(list.get(0));

        List<String> list = letterCombinations("234");
        System.out.println(list.get(0));
    }

    // 46.全排列
    public static List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (length == 0) {
            return result;
        }
        Deque<Integer> path = new ArrayDeque<>();
        int depth = 0;
        boolean[] used = new boolean[length];
        dfs(nums, result, length, path, depth, used);
        return result;
    }

    private static void dfs(int[] nums, List<List<Integer>> result, int length, Deque<Integer> path,
            int depth, boolean[] used) {
        if (depth == length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, result, length, path, depth + 1, used);
            path.removeLast();
            used[i] = false;
        }
    }

    //17. 电话号码的字母组合
    public static List<String> letterCombinations(String digits) {
        char[] numsArr = digits.toCharArray();
        int length = numsArr.length;
        List<String> resultList = new ArrayList<>();
        if (length == 0) {
            return resultList;
        }
        int depth = 0;
        boolean[] used = new boolean[length];
        List<Character> usedChar = new ArrayList<>();
        Deque<Character> path = new ArrayDeque<>();
        Map<Integer, List<Character>> map = getMap();
        combinationDFS(numsArr, length, depth, used, path, resultList, map, usedChar);
        return resultList;
    }

    private static void combinationDFS(char[] numsArr, int length, int depth, boolean[] used,
            Deque<Character> path, List<String> resultList, Map<Integer, List<Character>> map,List<Character> usedChar) {
        if (depth >= length) {
            StringBuilder stringBuilder = new StringBuilder();
            for(Character chara : path){
                stringBuilder.append(chara);
            }
            resultList.add(stringBuilder.toString());
            return ;
        }
        for (int i = 0; i < length; i++) {
            if(used[i]){
                continue;
            }
            List<Character> list = map.get(Integer.valueOf(String.valueOf(numsArr[i])));
            for (int j = 0; j < list.size(); j++) {

                path.addLast(list.get(j));
                used[i] = true;
                combinationDFS(numsArr, length, depth + 1, used, path, resultList, map, usedChar);
                used[i] = false;
                path.removeLast();
            }
            break;
        }
    }

    private static Map<Integer, List<Character>> getMap() {
        Map<Integer, List<Character>> map = new HashMap<Integer, List<Character>>(12);
        map.put(2, Arrays.asList('a', 'b', 'c'));
        map.put(3, Arrays.asList('d', 'e', 'f'));
        map.put(4, Arrays.asList('g', 'h', 'i'));
        map.put(5, Arrays.asList('j', 'k', 'l'));
        map.put(6, Arrays.asList('m', 'n', 'o'));
        map.put(7, Arrays.asList('p', 'q', 'r', 's'));
        map.put(8, Arrays.asList('t', 'u', 'v'));
        map.put(9, Arrays.asList('w', 'x', 'y', 'z'));
        return map;
    }

}
