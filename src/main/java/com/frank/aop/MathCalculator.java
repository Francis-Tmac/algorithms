package com.frank.aop;

/**
 * {@link  }
 *
 * @Date 2021/5/9
 * @Author frank
 * @Description:
 */
public class MathCalculator {

    public int div(int i, int j){
        System.out.println("execute MathCalculator#div");
        return i/j;
    }
}
