package com.frank;


import com.alibaba.fastjson.JSON;
import com.frank.algorithms.ListNode;
import com.frank.algorithms.leetcode.LeetCode;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fukangyang
 * @date 2022/6/16
 * @ desc
 */

public class Test {

    Map<Integer, List<TreeNode>> map = new HashMap<>();

    Integer minPath = Integer.MAX_VALUE;

    /**
     * 遍历树并且找到叶子节点
     *
     * @param root
     * @param parentPath
     * @return
     */
    public boolean findNodePath(TreeNode root, Integer parentPath) {
        if (root == null) {
            return true;
        }
        Integer curPath = parentPath + root.val;
        boolean left = findNodePath(root.left, curPath);
        boolean right = findNodePath(root.right, curPath);
        if (left && right) {
            minPath = Math.min(parentPath, minPath);
            List<TreeNode> tempList = map.get(curPath);
            if (CollectionUtils.isEmpty(tempList)) {
                tempList = new ArrayList<>();
            }
            tempList.add(root);
            //
            map.put(curPath, tempList);
        }
        return false;
    }


    public List<TreeNode> findMinPathNode(TreeNode root) {
        this.findNodePath(root, 0);
        return map.get(minPath);
    }


    public void findMaxAndMin(int[] arr) {
        MinMaxValue minMaxValue = find(arr, 0, arr.length - 1);
        int min = minMaxValue.min;
        int max = minMaxValue.max;
    }

    public MinMaxValue find(int[] arr, int left, int right) {
        if (right == left) {
            MinMaxValue minMaxValue = new MinMaxValue();
            minMaxValue.max = arr[left];
            minMaxValue.min = arr[left];
            return minMaxValue;
        }

        if (right - left == 1) {
            MinMaxValue minMaxValue = new MinMaxValue();
            minMaxValue.max = Math.max(arr[left], arr[right]);
            minMaxValue.min = Math.min(arr[left], arr[right]);
            return minMaxValue;
        }
        // 做分治处理
        int mid = (right - left) / 2 + left;
        MinMaxValue leftValue = find(arr, left, mid);
        MinMaxValue rightValue = find(arr, mid + 1, right);
        leftValue.min = Math.min(leftValue.min, rightValue.min);
        leftValue.max = Math.max(leftValue.max, rightValue.max);
        return leftValue;
    }

    /**
     * 基金净值在一定周期内，按照日期增大从最高点到最低点时的收益率，叫做最大回撤；
     * 比如给定一个周期内的基金净值 [3,7,9,6,4,1,9,8,5]，最大回撤值为7-1=6
     *
     * @param arr
     * @return
     */
    private int findMaxDown(int[] arr) {
        // 记录最大回撤值
        int maxDown = 0;
        // 记录从 [0,cur] 的最大值
        int curMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > curMax) {
                curMax = arr[i];
            } else {
                maxDown = Math.max(maxDown, curMax - arr[i]);
            }
        }
        return maxDown;
    }


    /**
     * 对数组nums 的 [left,right) 区间内的值进行一次partition
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int partition3(int[] nums, int left, int right, int target) {
        Random random = new Random();
        int v = random.nextInt((right - left)) + left;
        swapIndex(nums, left, v);

        int k = nums[left];
        // 定义区间[left + 1, lt] < k, [lt+1, i] == k, [gt, right] > k
        int lt = left, i = left + 1, gt = right + 1;
        while (i < gt) {
            if (nums[i] < k) {
                swapIndex(nums, lt + 1, i);
                lt++;
                i++;
            } else if (nums[i] > k) {
                swapIndex(nums, gt - 1, i);
                gt--;
            } else {
                i++;
            }
        }
        swapIndex(nums, left, lt);
        if (target >= left && target <= lt - 1) {
            return partition3(nums, left, lt - 1, target);
        } else if (target <= right && target >= gt) {
            return partition3(nums, gt, right, target);
        } else {
            return k;
        }
    }

    /**
     * 两路快排
     *
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    public int partition2(int[] nums, int left, int right, int target) {
        Random random = new Random();
        // 取随机位置
        int v = random.nextInt((right - left)) + left;
        swapIndex(nums, left, v);
        int k = nums[left];

        // 定义 [left + 1, lt] >= k, [gt, right] < k
        int i = left + 1, lt = left, gt = right + 1;
        while (i < gt) {
            if (nums[i] >= k) {
                i++;
                lt++;
            } else {
                swapIndex(nums, i, gt - 1);
                gt--;
            }
        }
        swapIndex(nums, left, lt);
        if (target == lt) {
            return k;
        } else if (target > i - 1) {
            return partition2(nums, gt, right, target);
        } else {
            return partition2(nums, left, i - 1, target);
        }
    }

    public int partition3_1(int[] nums, int left, int right, int target) {
        System.out.println(JSON.toJSON(nums));
        System.out.println("this left: " + left + ", right: " + right);
        if (left == right && left == target) {
            System.out.println("this is equals");
            return nums[left];
        }
        Random random = new Random();
        int k = random.nextInt(right - left) + left;
        swapIndex(nums, k, left);
        int v = nums[left];
        System.out.println("this k: " + k + ", v: " + v);
        // 定义区间 [left + 1, lt] > k; [lt + 1, i) == k; [gt, right] < k
        int lt = left, i = left + 1, gt = right + 1;
        while (i < gt) {
            if (nums[i] > v) {
                swapIndex(nums, i, lt + 1);
                i++;
                lt++;
            } else if (nums[i] < v) {
                swapIndex(nums, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        // lt 作为[left + 1, lt] 的右边界，和left 交换后 满足：[left , lt - 1] < k; [lt , i) == k
        swapIndex(nums, left, lt);
        if (target >= lt && target < i) {
            return v;
        } else if (target < lt) {
            return partition3_1(nums, left, lt - 1, target);
        } else {
            return partition3_1(nums, gt, right, target);
        }
    }

    /**
     * 快速排序思想
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return partition3_1(nums, 0, nums.length - 1, k);
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    set.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(set);
        return result;
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int high = triangle.size();
        int level = triangle.get(high - 1).size();
        // 存储当前乘级节点的最小路径
        int[] arr = new int[level];
        List<Integer> last = triangle.get(high - 1);
        for (int i = 0; i < level; i++) {
            arr[i] = last.get(i);
        }
        for (int j = high - 2; j >= 0; j--) {
            List<Integer> tempList = triangle.get(j);
            int k = tempList.size();
            for (int v = 0; v < k; v++) {
                arr[v] = tempList.get(v) + Math.min(arr[v], arr[v + 1]);
            }
        }
        return arr[0];
    }

    /**
     * 翻转列表
     *
     * @param head
     * @return
     */
    public ListNode reverseNode(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode reverseHead = reverseNode(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    public ListNode reverseKGroup_1(ListNode head, int k) {
        // 新建一个虚拟头节点
        ListNode firstNode = new ListNode(-1, head);
        ListNode preNode = firstNode;
        ListNode tempHead = head;
        // 当前的节点数量 k 个后会重复计算
        int curCount = 1;
        while (head != null) {
            if (curCount == k) {
                // 记录下一个节点
                ListNode nextNode = head.next;
                // 将当前节点与下一个节点切断，然后翻转
                head.next = null;
                preNode.next = reverseNode(tempHead);
                preNode = tempHead;
                preNode.next = nextNode;
                head = nextNode;
                tempHead = nextNode;
                curCount = 1;
            } else {
                head = head.next;
                curCount++;
            }
        }
        return firstNode.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tempHead = head;
        ListNode first = new ListNode(-1, head);
        ListNode preNode = first;
        int curNum = 1;
        while (head != null) {
            if (curNum == k) {
                ListNode nextHead = head.next;
                head.next = null;
                preNode.next = this.reverseNodeList(tempHead);
                preNode = tempHead;
                curNum = 1;
                head = nextHead;
                tempHead = nextHead;
            } else {
                head = head.next;
                curNum++;
            }
        }
        if (curNum != 1) {
            preNode.next = tempHead;
        }
        return first.next;
    }

    public ListNode reverseNodeList(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode reverseHead = reverseNodeList(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public int numIslands(char[][] grid) {
        int high = grid.length, level = grid[0].length;
        int count = 0;
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < level; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int high, int level) {
        if (!isInRangeSize(grid, high, level)) {
            return;
        }
        if (grid[high][level] != '1') {
            return;
        }
        grid[high][level] = '2';
        dfs(grid, high - 1, level);
        dfs(grid, high + 1, level);
        dfs(grid, high, level - 1);
        dfs(grid, high, level + 1);
    }

    public int maxAreaOfIsland(int[][] grid) {
        int high = grid.length, level = grid[0].length;
        int max = 0;
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < level; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfsMaxArea(grid, i, j));
                }
            }
        }
        return max;
    }

    private int dfsMaxArea(int[][] grid, int high, int level) {
        if (!isInRangeSize(grid, high, level)) {
            return 0;
        }
        if (grid[high][level] != 1) {
            return 0;
        }
        grid[high][level] = 2;
        int up = dfsMaxArea(grid, high - 1, level);
        int down = dfsMaxArea(grid, high + 1, level);
        int left = dfsMaxArea(grid, high, level - 1);
        int right = dfsMaxArea(grid, high, level + 1);
        return 1 + up + down + left + right;
    }

    private boolean isInRangeSize(int[][] grid, int high, int level) {
        return 0 <= high && high < grid.length && 0 <= level && level < grid[0].length;
    }

    private boolean isInRangeSize(char[][] grid, int high, int level) {
        return 0 <= high && high < grid.length && 0 <= level && level < grid[0].length;
    }


    public int islandPerimeter(int[][] grid) {
        int high = grid.length, level = grid[0].length;
        int perimeter = 0;
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < level; j++) {
                if (grid[i][j] == 1) {
                    perimeter += dfsPerimeter(grid, i, j);
                }
            }
        }
        return perimeter;
    }

    private int dfsPerimeter(int[][] grid, int high, int level) {
        if (!isInRangeSize(grid, high, level)) {
            return 1;
        }
        if (grid[high][level] == 0) {
            return 1;
        } else if (grid[high][level] == 2) {
            return 0;
        }
        grid[high][level] = 2;
        return dfsPerimeter(grid, high - 1, level) +
                dfsPerimeter(grid, high + 1, level) +
                dfsPerimeter(grid, high, level - 1) +
                dfsPerimeter(grid, high, level + 1);
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        } else if (root == p) {
            return p;
        } else if (root == q) {
            return q;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }

    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode virtualNode = new ListNode(-1, head);
        ListNode preNode = virtualNode;
        head = preNode;
        ListNode nextNode;
        int curCount = 0;
        while(head != null){
            if(curCount + 1 == left){
                preNode = head;
                head = head.next;
                curCount++;
            }else if (curCount == right){
                nextNode = head.next;
                head.next = null;
                ListNode tempHead = preNode.next;
                preNode.next = reversedNode(tempHead);
                tempHead.next = nextNode;
                break;
            }else{
                head = head.next;
                curCount++;
            }
        }
        return virtualNode.next;
    }

    public ListNode reversedNode(ListNode head){
        if(head.next == null){
            return head;
        }
        ListNode reverseHead = reversedNode(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfsSideView(root, list, 0);
        return list;
    }

    public void dfsSideView(TreeNode root, List<Integer> list, int level){
        if(root == null){
            return;
        }else if(list.size() < level + 1){
            list.add(root.val);
            dfsSideView(root.right, list, level + 1);
            dfsSideView(root.left, list, level + 1);
        }else {
            dfsSideView(root.right, list, level + 1);
            dfsSideView(root.left, list, level + 1);
        }
    }

    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        int max = 1;
        dp[0] = 1;
        for(int i = 1; i < length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3};
        char grid[][] = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        char grid1[][] = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        Test test = new Test();
        ListNode node_7 = new ListNode(7);
        ListNode node_6 = new ListNode(6, node_7);
        ListNode node_5 = new ListNode(5, node_6);
        ListNode node_4 = new ListNode(4, node_5);
        ListNode node_3 = new ListNode(3, node_4);
        ListNode node_2 = new ListNode(2, node_3);
        ListNode node_1 = new ListNode(1, node_2);
        int length = arr.length;
//        int result = test.numIslands(grid1);

        int grid2[][] = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        TreeNode t_1 = new TreeNode(1);
        TreeNode t_2 = new TreeNode(2);
        TreeNode t_3 = new TreeNode(3);
        TreeNode t_4 = new TreeNode(4);
        TreeNode t_5 = new TreeNode(5);
        TreeNode t_6 = new TreeNode(6);
        TreeNode t_7 = new TreeNode(7);
        t_1.left = t_2;
        t_1.right = t_3;
        t_3.right = t_4;
        t_2.left = t_5;
        t_5.left = t_6;
        t_6.right = t_7;
        List<Integer> result =  test.rightSideView(t_1);
//        int result = test.maxAreaOfIsland(grid2);
        System.out.println(JSON.toJSONString(result));
    }

    public List<List<Integer>> threeSum_1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int length = nums.length;
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < length - 2; i++) {
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int tempResult = nums[i] + nums[left] + nums[right];
                if (tempResult == 0) {
                    set.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                } else if (tempResult < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(set);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        boolean reverseFlag = false;
        while (!queue.isEmpty()) {
            int curLength = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < curLength; i++) {
                TreeNode cur = queue.removeFirst();
                tempList.add(cur.val);
                if (cur.left != null) {
                    queue.addLast(cur.left);
                }
                if (cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
            if (reverseFlag) {
                reverseList(tempList);
            }
            reverseFlag = !reverseFlag;
            result.add(tempList);
        }
        return result;
    }

    public void reverseList(List<Integer> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        int size = list.size();
        int mid = size / 2;
        for (int i = 0; i < mid; i++) {
            Integer temp = list.get(i);
            list.set(i, list.get(size - 1 - i));
            list.set(size - 1 - i, temp);
        }
    }


    /**
     * 交换函数
     *
     * @param nums
     * @param from
     * @param to
     */
    private void swapIndex(int[] nums, int from, int to) {
        if (from != to) {
            int temp = nums[from];
            nums[from] = nums[to];
            nums[to] = temp;
        }
    }


}
