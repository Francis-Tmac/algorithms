package com.frank.algorithms.sort;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * {@link  }
 *
 * @Date 2021/2/26
 * @Author frank.yang
 * @Description:
 */
public class QuickSort2 extends AbstractSort {
//    private void

    private void quickSort(Integer[] arr, int left, int right){
        if(left >= right){
            return ;
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,2L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(9),
                new ThreadPoolExecutor.CallerRunsPolicy());

//        threadPoolExecutor.execute();
    }


    @Override
    public void sort(Integer[] arr) {

    }

    @Override
    public String getSortName() {
        return "双指针快排";
    }
}
