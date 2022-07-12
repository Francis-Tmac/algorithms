package com.frank;

/**
 * @author fukangyang
 * @date 2022/6/27
 * @ desc
 */

public class LeetCode {

    public int search(int[] nums, int target) {
        int length = nums.length;
        int left = 0, right = length - 1;
        while(left < right){
            int mid = (left + right)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[0] < nums[mid]){
                if(nums[0] <= target && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return  -1;
    }

    public int numIslands(char[][] grid) {
        int high = grid.length, level = grid[0].length;
        int count = 0;
        for(int i = 0; i < high; i++){

            for(int j = 0; j < level; j++){
               if(grid[i][j] == '1'){
                   dfsIslands(grid, i, j);
                   count++;
               }
            }

        }
        return count;
    }

    private void dfsIslands(char[][] grid, int high, int level){
        if(!isInRange(grid, high, level)){
            return;
        }
        if(grid[high][level] != '1'){
            return;
        }
        grid[high][level] = '2';
        dfsIslands(grid, high + 1, level);
        dfsIslands(grid, high - 1, level);
        dfsIslands(grid, high, level + 1);
        dfsIslands(grid, high, level - 1);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1){
            return 0;
        }
        int[] dp = new int[col];
        dp[0] = 1;
        for(int i = 1; i < col; i++){
            if(obstacleGrid[0][i] == 1 || dp[i -1] == 0){
                dp[i] = 0;
            }else {
                dp[i] = 1;
            }
        }
        for(int j = 1; j < row; j++){
            if(obstacleGrid[j][0] == 1){
                dp[0] = 0;
            }
            for(int k = 1; k < col; k++){
                if(obstacleGrid[j][k] == 1){
                    dp[k] = 0;
                }else{
                    dp[k] = dp[k] + dp[k-1];
                }
            }
        }
        return dp[col - 1];
    }

    private boolean isInRange(char[][] grid, int high, int level){
        return high >= 0 && high < grid.length && level >= 0 && level < grid[0].length;
    }

}
