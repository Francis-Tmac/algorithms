package com.frank.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link  }
 *
 * @Date 2021/3/30
 * @Author frank.yang
 * @Description:
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> targetList = new ArrayList<>();
        if(nums.length == 0 || nums.length == 1){
            return targetList;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                return targetList;
            }
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int j = i + 1;
            int k = nums.length -1 ;
            int current = nums[i];
            while ( j < k){
                if(current + nums[j] + nums[k] == 0){
                    List<Integer> list = Arrays.asList(current, nums[j], nums[k]);
                    targetList.add(list);
                    while (j < k && nums[j] == nums[j+1]){
                        j++;
                    }
                    while (j < k && nums[k] == nums[k-1]){
                        k--;
                    }
                    k--;
                    j++;
                }else if(current + nums[j] + nums[k] > 0){
                    k--;
                }else {
                    j++;
                }
            }

        }
        return targetList;
    }
}
