package com.frank.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author fukangyang
 * @date 2022/6/15
 * @ desc
 */

public class WeightedRandom {

    private List<Integer> arr = new ArrayList<>();

    private Integer sum = 0;

    private int[] original;

    public WeightedRandom(int[] input) {
        // show me your code
       for(int i = 0; i < input.length; i++){
           sum += input[i];
           arr.add(sum);
       }
        original = input;
    }

    public int next() {
        // show me your code
        if(arr.isEmpty()){
            return 0;
        }
        Random random = new Random();
        int index = random.nextInt(sum);
        int left = 0, right = arr.size();
        while(left < right){
            int mid = (right - left)/2 + left;
            if(arr.get(mid) == index){
                return original[mid];
            }else if (arr.get(mid) > index){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return original[right];
    }

    public static void main(String[] args) {
        int[] input = new int[]{ 4, 2, 1, 3, 3};
        WeightedRandom weightedRandom = new WeightedRandom(input);
        for(int i = 0; i < 20; i++){

            System.out.println(weightedRandom.next());
        }
    }

    public static int getRandom(){
        Random random = new Random();
        return random.nextInt(10);
    }
}
