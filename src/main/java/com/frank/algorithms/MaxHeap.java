package com.frank.algorithms;


/**
 * {@link  }
 *
 * @Date 2021/5/24
 * @Author frank
 * @Description:
 */
public class MaxHeap {

    private Integer[] data;

    private int count;

    private int capacity;



    public MaxHeap(int capacity) {
        this.data = new Integer[capacity+1];
        this.count = 0;
        this.capacity = capacity;
    }

    public MaxHeap(Integer[] arr){
        capacity = arr.length;
        data = new Integer[capacity + 1];
        for (int i = 0; i < capacity; i++) {
            data[i+1] = arr[i];
        }
        count = capacity;
        for (int i = count/2; i >=1  ; i--) {
         shiftDown(i);
        }
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public Integer[] heapSort(Integer[] arr){
        int length = arr.length;
        MaxHeap maxHeap = new MaxHeap(length);

        for (int i = 0; i < length; i++) {
            insert(arr[i]);
        }
        for (int j = length-1; j >= 0; j--){
            arr[j] = maxHeap.extractMax();
        }

        return maxHeap.data;


    }

    public void insert(Integer item){
        if(capacity > count+1){
            System.out.println("over size");
        }
        data[count+1] = item;
        count++;
        shiftUp(count);
    }

    // 堆中插入一个元素
    private void shiftUp(int k) {
        while (data[k] > data[k/2]){
            swap(data,k,k/2);
            k = k/2;
        }
    }

    public Integer extractMax(){
        if(count > 0){

        }
        Integer ret = data[1];
        swap(data, 1, count);
        count--;
        shiftDown(1);
        return ret;

    }

    private void shiftDown(int i){
        while (i*2 < count){
            int j = i*2;
            if(j+1 <= count && data[j+1] > data[j]){
                j = j+1;
            }
            if(data[i] >= data[j])
            {break;}

            swap(data,i,j);
            i = j;
        }
    }

    private void swap(Object[] data,int i, int j){
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
