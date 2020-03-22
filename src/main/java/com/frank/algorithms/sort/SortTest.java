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

    private static final Integer ARRAY_SIZE = 80000;

    public static void main(String[] args) {
        Integer[] integerArray = SortUtil.getIntegerArray(ARRAY_SIZE,START,ARRAY_SIZE);
        SelectionSort sortAlgorithms = new SelectionSort();
        sortAlgorithms.testSortTime(sortAlgorithms.getSortName(),integerArray);
    }
}
