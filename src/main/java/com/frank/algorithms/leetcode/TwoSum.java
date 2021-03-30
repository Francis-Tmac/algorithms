package com.frank.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link  }
 *
 * @Date 2021/3/30
 * @Author frank.yang
 * @Description:
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(hashMap.containsKey(target - nums[i])){
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }
}
