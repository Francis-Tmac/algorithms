package com.frank.algorithms.sort;

import com.frank.algorithms.utils.SortUtil;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-22
 **/

public class SortTest {
    private static final Integer START = 0;

    private static final Integer ARRAY_SIZE = 150000;

    public static void main(String[] args) {
        /**
         * 构造相同的数组
         */
        Integer[] selectionArray = SortUtil.getIntegerArray(ARRAY_SIZE,START,ARRAY_SIZE);
        Integer[] insertionArray = SortUtil.copyArr(selectionArray);
        Integer[] mergeArray = SortUtil.copyArr(selectionArray);

        /***
         * 选择排序
         */
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.testSortTime(selectionSort.getSortName(),selectionArray);

        /***
         * 插入排序
         */
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.testSortTime(insertionSort.getSortName(),insertionArray);

        /***
         * 归并排序
         */
        MergeSort mergeSort = new MergeSort();
        mergeSort.testSortTime(mergeSort.getSortName(),mergeArray);
    }
}
