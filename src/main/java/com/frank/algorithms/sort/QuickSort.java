package com.frank.algorithms.sort;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-04-01
 **/

public class QuickSort extends AbstractSort {

    private int partition(Integer[] arr,int j, int k){
        Integer t = arr[j];
        System.out.println("j = " + j + ", k = " + k);
        Integer left = j;
        Integer right = k;
        // 定义[0,left] 为 <= t 的数组
        // [left,arr.length - 1] 为 > t 的区间
        // 当left >= right 结束循环
        while (left < right){
            if(arr[right] > t){
                right--;
                continue;
            }
            if(arr[left] <= t){
                left++;
                continue;
            }
            swap( left, right, arr);
            right--;
        }
        swap(j,left,arr);
        System.out.println("t: "+ arr[left]+", left: " + left);
        return left;
    }

    private void quick(Integer[] arr, int j, int k){
        if(j>=k){
            return;
        }
        int p = partition(arr, j, k);
        printArray(arr);
        quick(arr, j, p - 1);
        quick(arr, p + 1, k);
    }


    @Override
    public void sort(Integer[] arr) {
        quick(arr,0,arr.length-1);
    }

    @Override
    public String getSortName() {
        return "快速排序-";
    }

    private void swap(int from, int to, Integer[] arr){
        if(to == from){
            return;
        }
        Integer temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
        return;
    }
}
