package com.frank.algorithms.sort;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-22
 **/

public abstract class AbstractSort implements SortAlgorithms{


    protected void testSortTime(String string, Integer[] integerArray){
        Long startTime = System.currentTimeMillis();
        this.sort(integerArray);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        BigDecimal bigDecimal = new BigDecimal(time).movePointLeft(3);
        System.out.println(string + "算法共消耗： "+ bigDecimal +" 秒");
    }

    protected   void exchange(Integer[] integerArray, int from, int to){
        Integer temp = integerArray[from];
        integerArray[from] = integerArray[to];
        integerArray[to] = temp;
    }

    protected  void printArray(Integer[] integerArray){
        StringBuilder sb = new StringBuilder();
        sb.append("this integerArray : ");
        for (int i = 0; i < integerArray.length; i++){
            sb.append(integerArray[i] + ", ");
        }
        System.out.println(sb);
    }
}
