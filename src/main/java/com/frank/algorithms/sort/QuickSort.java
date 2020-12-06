package com.frank.algorithms.sort;

import com.frank.algorithms.QuickReturnDTO;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-04-01
 **/

public class QuickSort extends AbstractSort {

    private QuickReturnDTO partition(Integer[] arr, int j, int k){
        QuickReturnDTO quickReturn = new QuickReturnDTO();
        Integer t = arr[j];
        System.out.println("j = " + j + ", k = " + k);
        Integer left = j;
        Integer i = j+1;
        Integer right = k;
        // 定义[0,left] 为 <= t 的数组
        // [left,arr.length - 1] 为 > t 的区间
        // 当left >= right 结束循环
        System.out.println("T: " + arr[j]);
        while(i < right){
            if(arr[i] < t){
               swap(i, left++, arr);
               i++;
            }
            if(arr[i] > t){
                swap(i,right,arr);
                right--;
            }
            if(arr[i].equals(t)){
                i++;
            }
        }
        System.out.println("j: " + arr[j] + " left: " + arr[left]);




//        while (!i.equals(right)){
//            if(arr[i] < t){
//                left++;
//                i++;
//                continue;
//            }
//            if(arr[right] > t){
//                right--;
//                continue;
//            }
//
//            swap( left, right, arr);
//            right--;
//        }
//        swap(j,left,arr);
//        System.out.println("t: "+ arr[left]+", left: " + left);
        quickReturn.setLeft(left);
        quickReturn.setRight(right);
        return quickReturn;
    }

    private void quick(Integer[] arr, int j, int k){
        if(j>=k){
            return;
        }
        System.out.println("排序前： ");
        printArray(arr);
        QuickReturnDTO quickReturn = partition(arr, j, k);
        System.out.println("quickReturn :"+ quickReturn.toString());
        System.out.println("排序后： ");
        printArray(arr);
        quick(arr, j, quickReturn.getLeft()-1);
        quick(arr, quickReturn.getRight(), k);
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
