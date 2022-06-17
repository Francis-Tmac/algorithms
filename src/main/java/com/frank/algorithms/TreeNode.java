package com.frank.algorithms;

/**
 * @author fukangyang
 * @date 2022/6/13
 * @ desc
 */

public class TreeNode {

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
