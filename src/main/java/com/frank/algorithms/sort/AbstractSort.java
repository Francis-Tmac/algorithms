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

    /***
     * 输出数组间隔三位数字
     */
    public static final int SEP_NUMBER = 3;

    protected void testSortTime(String string, Integer[] integerArray){
        Long startTime = System.currentTimeMillis();
        this. sort(integerArray);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        BigDecimal bigDecimal = new BigDecimal(time).movePointLeft(3);
        System.out.println(string + "算法，此排序数组大小为: "+ getStringInteger(integerArray.length) +" 共消耗： "+ bigDecimal +" 秒");
        isSorted(integerArray);
    }

    protected   void swap(Integer[] integerArray, int from, int to){
        Integer temp = integerArray[from];
        integerArray[from] = integerArray[to];
        integerArray[to] = temp;
    }

    protected void isSorted(Integer[] integerArray){
        for(int i = 0; i < integerArray.length-1; i++){
            if(integerArray[i]>integerArray[i+1]){
                System.out.println("非从小到大数组！！！！！！！！！！！！！");
            }
        }
        System.out.println("--------------有序数组");
    }


    protected  void printArray(Integer[] integerArray){
        StringBuilder sb = new StringBuilder();
        sb.append("this integerArray : ");
        for (int i = 0; i < integerArray.length; i++){
            sb.append(integerArray[i] + ", ");
        }
        System.out.println(sb);
    }

    private  StringBuilder getStringInteger(Integer integer){
        int length = getLength(integer);
        int index = (length / 3);
        if(length % SEP_NUMBER == 0){
            index = index-1;
        }
        StringBuilder sb = new StringBuilder();
        Integer last = integer;
        for(int i = 0; i < index ; i++){
            Integer temp = last % 1000;
            String tempString ;
            if(temp.equals(0)){
                tempString = ","+"000";
            }else if(temp < 10){
                tempString = ",00" + temp.toString() ;
            }else if(temp < 100){
                tempString = ",0" + temp.toString() ;
            }else {
                tempString = "," + temp.toString();
            }
            last = last/1000;
            sb.insert(0,tempString);
        }
        sb.insert(0,last.toString());
        return sb;
    }

    private  int getLength(Integer integer){
        if(integer < 0){
            throw new IllegalArgumentException();
        }
        int size = 1 ;
        Integer temp = integer;
        for(Boolean i = true; i;){
            temp = temp / 10;
            if(temp < 1){
                i = false;
            }else {
                size++;
            }
        }
        return size;
    }
}
