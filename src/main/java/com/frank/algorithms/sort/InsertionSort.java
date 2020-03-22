package com.frank.algorithms.sort;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-22
 **/

public class  InsertionSort extends AbstractSort {

    @Override
    public void sort(Integer[] arr) {
        for(int i = 1; i < arr.length; i++){
            /*int temp = i-1;
            int j = i;
            while ( temp >= 0 && arr[temp] > arr[j]){
                swap(arr,temp,j);
                temp = temp - 1;
                j = j -1;
            }*/
            /*for(int k = i ; k > 0 ; k--){
                if(arr[k-1] > arr[k]){
                    swap(arr,k-1,k);
                }else{
                    break;
                }
            }*/
            Integer temp = arr[i];
            int k = i-1;
            for(; k >= 0 && temp < arr[k]; k--){
                arr[k+1] = arr[k];
            }
            arr[k+1] = temp;
        }
    }

    @Override
    public String getSortName() {
        return "插入排序-";
    }
}
