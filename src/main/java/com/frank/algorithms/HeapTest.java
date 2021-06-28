package com.frank.algorithms;

import com.frank.algorithms.leetcode.LeetCode;
import com.frank.algorithms.leetcode.LeetCode.ListNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * {@link  }
 *
 * @Date 2021/3/2
 * @Author frank.yang
 * @Description:
 */
public class HeapTest {

    // 100kb

    byte[] a = new byte[1024*100];

    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList();
        List<List<Integer>> target = new ArrayList();
        queue.addLast(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList();
            for(int i = 0; i < size; i++){
                TreeNode temp = queue.getFirst();
                list.add(temp.val);
                if(temp.left != null){
                    queue.addLast(temp.left);
                }
                if(temp.right != null){
                    queue.addLast(temp.right);
                }
                temp = null; // help gc
            }
            target.add(list);
        }
        return target;

    }

     class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static void main(String[] args) throws Exception{
//        List<HeapTest> list = new ArrayList<>();
//        while (true){
//            list.add(new HeapTest());
//            TimeUnit.MILLISECONDS.sleep(10);
//        }
//        String split = " ";
//        String ss = "  hello world!  ";
//        StringBuilder sb = new StringBuilder();
//        String[] strings = ss.split(split);
//        int length = strings.length;
//        boolean isFirst = true;
//        for(String s : strings){
//            if(s.equals("")){
//                continue;
//            }
//        }
//        for(int i = length -1 ; i >= 0; i--){
//            if(strings[i].equals("")){
//                continue;
//            }
//            sb = isFirst  ? sb.append(strings[i]) : sb.append(split + strings[i]);
//            isFirst = false;
//        }
//        System.out.println(sb.toString());

        int[] arr = new int[]{0,2};
        System.out.println(missingNumber(arr));
    }

    public static int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(true) {
            if(right == left) {
                if(nums[left] == left) {
                    return left + 1;
                }
                return left;
            }
            int mid = (right - left) / 2 + (right - left) % 2 + left;
            if (mid == nums[mid]) {
                left = mid;
            }else {
                right = mid;
            }
        }
    }
    public int missingNumber1(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
    }

    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(set.contains(head)){
                return false;
            }
            set.add(head);
//            head = head.next;
        }
        return true;
    }

}
