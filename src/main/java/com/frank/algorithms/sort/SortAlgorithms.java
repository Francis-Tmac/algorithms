package com.frank.algorithms.sort;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-22
 **/

public interface SortAlgorithms {

    /****
     * 对数组进行排序
     * @param arr
     */
    void sort(Integer[] arr);

    /***
     * 得到排序方法的名字
     * @return
     */
    String getSortName();
}
