package com.frank.algorithms.sort;

import com.frank.algorithms.utils.SortUtil;
import com.sun.webkit.dom.XPathResultImpl;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-22
 **/

public class SortTest {

    private static final Integer START = 0;

    private static final Integer ARRAY_SIZE = 20;

    public static void main(String[] args) {
        Integer[] integerArray = SortUtil.getIntegerArray(ARRAY_SIZE,START,100);
        printArray(integerArray);
        selectionSort(integerArray);
        printArray(integerArray);
    }

    private Integer[] selectionSort(Integer[] integerArray, int size){
        Integer[] integers = new Integer[size];
        return integers;
    }

    private static void selectionSort(Integer[] integerArray){
        for(int i = 0; i < integerArray.length ; i++){
            int target = integerArray[i];
            int k = i;
            for(int j = i + 1; j < integerArray.length; j ++){
                if( target > integerArray[j]){
                    target = integerArray[j];
                    k = j;
                }
            }
            exchange(integerArray,i,k);
        }
    }

    private static void exchange(Integer[] integerArray, int from, int to){
        Integer temp = integerArray[from];
        integerArray[from] = integerArray[to];
        integerArray[to] = temp;
    }

    private static void printArray(Integer[] integerArray){
        StringBuilder sb = new StringBuilder();
        sb.append("this integerArray : ");
        for (int i = 0; i < integerArray.length; i++){
            sb.append(integerArray[i] + ", ");
        }
        System.out.println(sb);
    }

}
