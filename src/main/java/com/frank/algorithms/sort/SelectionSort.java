package com.frank.algorithms.sort;

import com.frank.algorithms.utils.SortUtil;
import com.sun.webkit.dom.XPathResultImpl;

/**
 * @program: algorithms
 * @description:
 * @author: Francis-Tmac
 * @create: 2020-03-22
 **/

public class SelectionSort extends AbstractSort{

    @Override
    public void sort(Integer[] integerArray) {
        for(int i = 0; i < integerArray.length ; i++){
            int minIndex = i;
            for(int j = i + 1; j < integerArray.length; j ++){
                if( integerArray[minIndex] > integerArray[j]){
                    minIndex = j;
                }
            }
            super.swap(integerArray,i,minIndex);
        }
    }

    @Override
    public String getSortName() {
        return "选择排序-";
    }
}
