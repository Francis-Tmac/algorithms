package com.frank;

import lombok.Data;

/**
 * @author fukangyang
 * @date 2022/6/17
 * @ desc
 */

public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public Integer val;


    public TreeNode(Integer val) {
        this.val = val;
    }
}
