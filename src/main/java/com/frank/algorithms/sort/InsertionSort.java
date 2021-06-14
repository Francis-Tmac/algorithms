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


    public static void main(String[] args) {
        int[] arrA = new int[]{1,2,3,4};
        int[] arrB = new int[]{3,4,5,6};
        InsertionSort is = new InsertionSort();
        int [] target = is.mergeSort(arrA,arrB);
        System.out.println(target[1]);
    }


    public int[]  mergeSort(int[] arrA, int[] arrB){
        int lengthA = arrA.length;
        int lengthB = arrB.length;
        int[] target = new int[lengthA + lengthB];
        // 分别定义三个数组的指针
        int i = 0, j = 0, k = 0;

        while ( i < lengthA || j < lengthB){
            if(i == lengthA){
                target[k] = arrB[j];
                j++;
            }else if(j == lengthB){
                target[k] = arrA[i];
                i++;
            }else if(arrA[i] > arrB[j]){
                target[k] = arrB[j];
                j++;
            }else {
                target[k] = arrA[i];
                i++;
            }
            k++;
        }
        return target;
    }


}
