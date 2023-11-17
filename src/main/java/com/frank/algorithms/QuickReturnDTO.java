package com.frank.algorithms;

import lombok.Data;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-04-02
 **/
@Data
public class QuickReturnDTO {

    private Integer left;

    private Integer right;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }
}
