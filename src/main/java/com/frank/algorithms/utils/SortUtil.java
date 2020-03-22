package com.frank.algorithms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-22
 **/

public class  SortUtil {

    public static List<Integer> getIntegerList(int size, Integer rangeL, Integer rangR){
        if(rangeL >= rangR){
            throw new IllegalArgumentException();
        }
        List<Integer> integerList = new ArrayList<Integer>(size);
        int j;
        Random random = new Random();
        for(int i = 0; i < size; i++){
            j = random.nextInt(rangR - rangeL + 1) + rangeL;
            integerList.add(j);
        }
        return integerList;
    }

    /**
     * 生成一个大小为size, 范围在 [rangL ， rangR] 的数组
     * @param size
     * @param rangeL
     * @param rangR
     * @return
     */
    public static Integer[] getIntegerArray(int size, Integer rangeL, Integer rangR){
        Integer[] integerArray = new Integer[size];
        int j;
        Random random = new Random();
        for(int i = 0; i < size; i++){
            j = random.nextInt(rangR - rangeL + 1) + rangeL;
            integerArray[i] = j;
        }
        return integerArray;
    }

    /***
     * 复制数组
     * @param arr
     * @return
     */
    public static Integer[] copyArr(Integer[] arr){
        Integer[] arrCopy = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++){
            arrCopy[i] = arr[i];
        }
        return arrCopy;
    }
}
