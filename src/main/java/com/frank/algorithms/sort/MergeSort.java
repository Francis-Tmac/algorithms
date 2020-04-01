package com.frank.algorithms.sort;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-24
 **/

public class MergeSort extends AbstractSort {


    /***
     * 递归使用归并排序对[left,right] 之间排序
     * @param arr
     * @param left
     * @param right
     */
    private void _mergeSort(Integer[] arr, int left, int right){

        if(left >= right){
            return ;
        }

        int mid = (right - left)/2 + left;
        // [left,mid] , (mid, right]
        _mergeSort( arr, left, mid);
        _mergeSort( arr,mid+1, right);
        /*if( arr[mid] > arr[mid + 1]){
            merge( arr, left, mid , right);
        }*/
        merge( arr, left, mid , right);
    }

    /***
     * 开始合并两个数组
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private void merge(Integer[] arr, int left, int mid, int right){
        //开始对两个数组组合
        Integer[] aux = copyArray(arr, left, right);
        /***
         * 将两个数组合并[left,mid] , [mid + 1, right]
         */
        //aux 数组的右边界下角标
        int t = aux.length - 1;
        //auc 数组的左边界的下角标
        int l = 0;

        int auxMid = (t-l) / 2;
        //左数组开始的位置
        int j = 0;
        //右数组开始的位置
        int k = auxMid +1 ;
        for(int i = left; i <= right; i++ ){
            if( j > auxMid){
                arr[i] = aux[k];
                k++;
            }else if( k > t){
                arr[i] = aux[j];
                j++;
            }else if( aux[k] >= aux[j]){
                arr[i] = aux[j];
                j++;
            }else if( aux[k] < aux[j]){
                arr[i] = aux[k];
                k++;
            }
        }
    }


    @Override
    public void sort(Integer[] arr) {
        _mergeSort(arr,0, arr.length -1);
    }

    @Override
    public String getSortName() {
        return "归并排序-";
    }

    private Integer[] copyArray(Integer[] arr, int left, int right){
        int size = right - left + 1;
        Integer[] aux = new Integer[size];
        for (int i = 0 ; i < size ; i++){
            aux[i] = arr[i+left];
        }
        return aux;
    }
}
