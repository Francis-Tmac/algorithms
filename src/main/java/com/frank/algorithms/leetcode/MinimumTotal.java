package com.frank.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link  }
 *
 * @Date 2021/6/14
 * @Author frank
 * @Description:
 */
public class MinimumTotal {

    public  static int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        if(length == 1){
            return triangle.get(0).get(0);
        }
        int[] arr = new int[length];
        List<Integer> lastList = triangle.get(length -1);
        for(int k = 0 ; k < length; k++){
            arr[k] = lastList.get(k);
        }
        for(int i = length - 2; i >= 0 ; i--){
            List<Integer> list = triangle.get(i);
            int subLength =list.size();
            for(int j = 0; j < subLength; j++){
                arr[j] = Math.min(arr[j],arr[j + 1]) + list.get(j);
            }
        }
        return arr[0];
    }


    // 动态规划求解
    private static int[] exchangeDP(int amount, int[] coins) {
        // 求最短路径，计算从 1-amount 上每个节点的最短路径
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++){
            dp[i] = amount + 1;
        }
        dp[0] = 0;
        for (int i = 0; i <= amount; i++){
            for (int j = 0; j < coins.length; j++){
                if(i >= coins[j]){
                    dp[i] = Math.min((dp[i - coins[j]]+1), dp[i]);
                }
            }
        }
        int minPath = dp[amount];
        int[] arr = new int[minPath];
        int left = amount;
        for(int i = 0; i < minPath; i++){
            for(int j = 0; j < coins.length; j++){
                if(dp[left - coins[j]] == minPath - i - 1) {
                    left  -= coins[j];
                    arr[i] = coins[j];
                    break;
                }
            }
        }
        return arr;
    }

    public static int cuttingRope(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i=3;i<=n;++i){
            // i-j >= 2
            for (int j=1;j<=i-2;++j){
                // 这里因为m>1，所以dp[2] = 1而不是2，dp[3]不能是dp[2] * 1，这样答案是1，错误.
                // 因此下面要添加Math.max(dp[i-j], i-j)。
                int k = Math.max(dp[i-j], i-j) * j;
                dp[i] = Math.max(k, dp[i]);

            }
        }
        return dp[n];
    }

    static int[] test=new int[]{0};
    public static int sumNums(int n) {
        try{
            return test[n];
        }catch(Exception e){
            return n+sumNums(n-1);
        }
    }

    public static int[][] findContinuousSequence(int target) {
        int k = target%2;
        int i = target/2 + k;
        List<int[]> targetList = new ArrayList();
        for(int j = i; j > 0; j--){
            List<Integer> list = new ArrayList();
            int[] aux = list.stream().mapToInt(Integer::intValue).toArray();
            int result = 0;
            for(int x = 1; x <= j; x++){
                result += x;
                list.add(x);
                if(result == target){
                    int[] arr = list.stream().mapToInt(Integer::valueOf).toArray();
                    targetList.add(arr);
                    break;
                }else if(result < target){
                    continue;
                }else {
                    list = null; // help gc
                    break;
                }
            }
        }
        int[][] targetArr = new int[targetList.size()][];
        for (int j = 0; j < targetList.size(); j++) {
            targetArr[j] = targetList.get(j);
        }
        return targetArr;
    }

    public static void main(String[] args) {

//        List<Integer> list_1 = Arrays.asList(2);
//        List<Integer> list_2 = Arrays.asList(3,4);
//        List<Integer> list_3 = Arrays.asList(6,5,7);
//        List<Integer> list_4 = Arrays.asList(4,1,8,3);
//        List<List<Integer>> list = Arrays.asList(list_1, list_2, list_3, list_4);
//        System.out.println(minimumTotal(list));

//        int[] result = exchangeDP(15,new int[]{1,2,5});
//        System.out.println(result);

        System.out.println(findContinuousSequence(9));
    }
}
