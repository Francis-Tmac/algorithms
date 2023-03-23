package com.frank;


import com.alibaba.fastjson.JSON;
import com.frank.algorithms.ListNode;
import com.frank.algorithms.leetcode.LeetCode;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        int length = nums.length;
        for(int i = 0; i < length; i++){
            if(map.get(target - nums[i]) != null){
                return new int[]{i, map.get(target - nums[i])};
            }else{
                map.put(nums[i],i);
            }
        }
        return null;
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
        while (head != null) {
            if (curCount + 1 == left) {
                preNode = head;
                head = head.next;
                curCount++;
            } else if (curCount == right) {
                nextNode = head.next;
                head.next = null;
                ListNode tempHead = preNode.next;
                preNode.next = reversedNode(tempHead);
                tempHead.next = nextNode;
                break;
            } else {
                head = head.next;
                curCount++;
            }
        }
        return virtualNode.next;
    }

    public ListNode reversedNode(ListNode head) {
        if (head.next == null) {
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

    public void dfsSideView(TreeNode root, List<Integer> list, int level) {
        if (root == null) {
            return;
        } else if (list.size() < level + 1) {
            list.add(root.val);
            dfsSideView(root.right, list, level + 1);
            dfsSideView(root.left, list, level + 1);
        } else {
            dfsSideView(root.right, list, level + 1);
            dfsSideView(root.left, list, level + 1);
        }
    }

    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int numIslands1(char[][] grid) {
        int count = 0;
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    dfsIsLand(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfsIsLand(char[][] grid, int row, int col) {
        if (!isInRange(grid, row, col)) {
            return;
        }
        if (grid[row][col] != '1') {
            return;
        }
        grid[row][col] = '2';
        dfsIsLand(grid, row - 1, col);
        dfsIsLand(grid, row + 1, col);
        dfsIsLand(grid, row, col - 1);
        dfsIsLand(grid, row, col + 1);
    }

    public boolean isInRange(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }


    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(0,0, n, "", result);
        return result;
    }

    public void dfs(int left, int right, int n, String ss, List<String> result) {
        if (ss.length() == n * 2) {
            result.add(ss);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, n, ss + "(", result);
        }
        if(left > right){
            dfs(left, right + 1, n , ss + ")", result);
        }

    }


    private String procString(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> numStack = new Stack();
        Stack<String> strStack = new Stack();
        char[] charArr = s.toCharArray();
        int length = charArr.length;
        String tempStr = "";
        for (int i = 0; i < length; i++) {
            // 0 - 10 之间入数字栈
            if ('0' < charArr[i] && charArr[i] <= '9') {
                numStack.push(charArr[i] - '0');
                if (tempStr != "") {
                    strStack.push(tempStr);
                    tempStr = "";
                }
            } else if ('a' <= charArr[i] && charArr[i] <= 'z') {
                tempStr = tempStr + charArr[i];
            } else if (')' == charArr[i]) {
                if (numStack.size() == 0 && strStack.size() == 0) {
                    continue;
                }
                strStack.push(tempStr);
                tempStr = strStack.pop();
                System.out.println(numStack.size() + " strStack size: " + strStack.size());
                String newString = algString(numStack.pop(), tempStr);

                while (!strStack.isEmpty()) {
                    newString = strStack.pop() + newString;
                    newString = algString(numStack.pop(), newString);
                }
                tempStr = "";
                result.append(newString);
            } else {
                continue;
            }
        }
        return result.toString();
    }

    private String algString(Integer num, String s) {
        System.out.println(num + " s : " + s);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(s);
        }
        System.out.println(result);
        return result.toString();
    }


    List<List<Integer>> result = new ArrayList<>();

    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        procCombine(n, k , 1);
        return result;
    }

    public void procCombine(int n, int k , int startIndex){
        if(path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = startIndex; i <= n; i++){
            path.addLast(i);
            procCombine(n, k, i + 1);
            path.removeLast();
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        procCombine3(k,n,0,1);
        return result;
    }

    public void procCombine3(int k, int n, int sum, int startIndex){
        if(sum == n && path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }else if(sum > n || path.size() >= k){
            return;
        }
        for(int i = startIndex; i <= Math.min(9, n); i++){
            sum += i;
            path.addLast(i);
            procCombine3(k, n , sum, i + 1);
            sum -= i;
            path.removeLast();
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0 , up = 0, right = matrix[0].length - 1, down = matrix.length - 1;
        List<Integer> result = new ArrayList<>();
        while(true){
            for(int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
            if(++up > down) {
                break;
            }

            for(int i = up; i <= down; i++){
                result.add(matrix[i][right]);
            }
            if(--right < left){
                break;
            }

            for(int i = right; i >= left; i--){
                result.add(matrix[down][i]);
            }
            if(--down < up){
                break;
            }

            for(int i = down; i >= up; i--){
                result.add(matrix[i][left]);
            }
            if(++left > right){
                break;
            }
        }
        return result;
    }




    public int lengthOfLongestSubstring(String s){
        if(s == null){
            return 0;
        }
        char[] arr = s.toCharArray();
        // 定义 [left, right] 区间内的字符都不重复
        int left = 0, right = 0, max = 1, length = arr.length;
        Set<Character> set = new HashSet<>();
        while ( right < length){
            if(set.contains(arr[right])){
                while(arr[left] != arr[right]){
                    set.remove(arr[left++]);
                }
                left++;
            }else {
                set.add(arr[right]);
                max = Math.max(max, right - left + 1);
            }
            right++;
        }
        return max;

    }

    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        procGenerateParenthesis(0, 0, result, 3, "");
        return result;
    }

    public void procGenerateParenthesis(int left, int right, List<String> result, int n, String temp){
        if(left == n && right == n){
            result.add(temp);
            return;
        }
        if(left < n){
            procGenerateParenthesis(left + 1, right, result, n , temp + "(");
        }
        if(left > right){
            procGenerateParenthesis(left, right + 1, result, n, temp + ")");
        }
    }



    public int[][] merge(int[][] intervals){
        if(intervals.length == 0){
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            int left = intervals[i][0], right = intervals[i][1];
            if(list.isEmpty() || list.get(list.size() - 1)[1] < left){
                list.add(new int[]{left, right});
            }else{
                list.get(list.size() - 1)[1] = Math.max(right, list.get(list.size() - 1)[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    List<List<Integer>> res= new ArrayList<>();

    LinkedList<Integer> temp = new LinkedList<>();

    public List<List<Integer>> combine4(int n, int k) {
        combineProc(n, k , 1);
        return res;
    }

    public void combineProc(int n, int k, int index){
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i <= n; i++){
            temp.add(i);
            combineProc(n, k, index + 1);
            temp.removeLast();
        }
    }

    int t;

    // 旋转前的尾结点
    ListNode lastNode;

    // 旋转后的头节点
    ListNode firsNode;

    public ListNode rotateRight(ListNode head, int k) {
        ListNode tempNode = head;

        searchIndex(head, k, 1);
        if(firsNode == head){
            return head;
        }

        lastNode.next = tempNode;
        return firsNode;
    }

    // 返回修改链表前当前Node 倒数的index
    public int searchIndex(ListNode cur, int k, int count){
        if(cur.next == null){
            lastNode = cur;
            // 对k 取余得到，t 满足 1 <= t <= count
            t = k % count;
            if(t == 1){
                firsNode = cur;
            }
            return 1;
        }
        int i = searchIndex(cur.next, k, count + 1) + 1;
        if(i == t){
            firsNode = cur;
        }else if (t + 1 == i){
            // 将旋转后的尾结点置为null 防止环
            cur.next = null;
        }
        return i;
    }

    public void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur);
            cur = cur.next;
        }
        int left = 0, right = list.size() - 1;
        while(left < right){
            list.get(left).next = list.get(right);
            left++;
            if(left == right){
                break;
            }
            list.get(right).next = list.get(left);
            right--;
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Map<String, List<String>> preDepMap = getMap();
//        HashMap<String, String> map = new HashMap<>(1000);
//        int count = 0;
//        for (Map.Entry<String, List<String>> entry : preDepMap.entrySet()) {
//            List<String> list = entry.getValue();
//            map.put(entry.getKey(), JSON.toJSONString(list));
//            list.clear();
//            ++count;
//            if (map.size() >= 1000 || count >= preDepMap.size()) {
//                // map累计数量达到指定阈值,先分批提交到 redis
//                map = new HashMap<>(1000);
//            }
//        }
        try{
            throw new NullPointerException();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private static Map<String, List<String>> getMap(){
        Map<String, List<String>> map = new HashMap<>(10000);
        for(int i = 0; i < 10000 ; i++){
            String key = i + "_key";
            List<String> list = new ArrayList<>(1000);
            for(int j = 0; j < 1000; j++){
                String value = i + "_" + j + "_value";
                list.add(value);
            }
            map.put(key, list);
        }
        return map;
    }












//    public static void main(String[] args) {
//        Test test = new Test();
//        List<String> re = test.generateParenthesis1(3);
//        List<List<Integer>> result = test.combinationSum3(9,45);
//        System.out.println(re);
//        List<Integer> a = Lists.newArrayList(1,2,3);
//        List<Integer> b = Lists.newArrayList(1,2,3,4);
//        b.removeAll(a);
//        System.out.println(JSON.toJSON(b));
//        String ss = "sqoop import \\\\\\n--connect 'jdbc:mysql://://10.74.48.74:3306/datamax?useUnicode=true&characterEncoding=utf-8' \\\\\\n--username 'root' \\\\\\n--password '******' \\\\\\n--query 'SELECT `a`,`datasourceId` from datamax.data_integration_endpoint'\\\" WHERE 1 AND \\\\$CONDITIONS \\\\\\n--m 1 \\\\\\n--delete-target-dir \\\\\\n--target-dir /user/hive/temp/default/order \\\\\\n--null-string '\\\\\\\\N' \\\\\\n--null-non-string '\\\\\\\\N' \\\\\\n--fields-terminated-by \\\"\\\\036\\\" \\\\\\n--hive-drop-import-delims \\\\\\n--fetch-size 10000 \\\\\\n--driver com.mysql.cj.jdbc.Driver \\\\\\n--mapreduce-job-name jobName \\\\\\nLOAD DATA INPATH '/user/hive/temp/default/order' OVERWRITE INTO TABLE default.order";
//        System.out.println(ss);
//        SubTaskDTO taskDTO1 = new SubTaskDTO();
//        taskDTO1.setTaskName("10.16.153.48:1530/smeststuat?connectTimeOut=2000");
//        SubTaskDTO taskDTO2 = new SubTaskDTO();
//        taskDTO2.setTaskName("10.16.153.48:1530/smeststuat");
//        List<SubTaskDTO> taskDTOList = Arrays.asList(taskDTO1, taskDTO2);
//        Set<String> set = taskDTOList.stream().map(SubTaskDTO::getTaskName).collect(Collectors.toSet());
//        System.out.println(JSON.toJSON(set));

//        String url = "10.16.153.48:1530/smeststuat?connectTimeOut=2000";
//        if(url.contains("?")){
//            int index = url.indexOf("?");
//            url = url.substring(0, index);
//        }
//        System.out.println(url);

//        String path = "taskInstance=TASK-20230215-1621815729458339842_1-605-194508894_:2_:datamax_:test_mapping";
//        int index = path.indexOf("_:");
//        String tableKey = path.substring(index+2);
//        System.out.println(tableKey.replace("_:","_"));

//        String ss = "id,code,age";
//        List<String> priKeyList = Arrays.asList(ss.split(","));
//        String[] keyArr = priKeyList.stream().map(Test::getJoinKey).toArray(String[]::new);
//        System.out.println(StringUtils.join(keyArr," and "));
//
//        String[] whereArr = priKeyList.stream().map(Test::getWhereCondition).toArray(String[]::new);
//        System.out.println(StringUtils.join(whereArr," and "));

//        List<String> arrList = new ArrayList<>();
//        List<String> ignoreList = new ArrayList<>();
//        arrList.add("ssss");
//        arrList.add("ddddd");
//        for (String ss : arrList){
//            if("ssss".equals(ss)){
//                ignoreList.add(ss);
//            }
//        }
//        arrList.removeAll(ignoreList);
//        System.out.println(JSON.toJSONString(arrList));
//
//        List<String> list = new ArrayList<>(Collections.emptyList());
//        list.add(null);
//        list.add(null);
//        System.out.println(list.size());
//    }

    private static String getJoinKey(String key){
        return "a." + key + "=" + "b." + key;
    }

    private static String getWhereCondition(String key){
        return "b." + key + " IS NULL";
    }

    private static String getTableName(String jobName){
        int index = jobName.lastIndexOf("_:");
        return jobName.substring(index + 2);
    }

    private static String getDbName(String jobName){
        int index = jobName.lastIndexOf("_:");
        String executeKey = jobName.substring(0,index);
        int dbIndex = executeKey.lastIndexOf("_:");
        return executeKey.substring(dbIndex+2);
    }

    public static void main_1(String[] args) {
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
        List<Integer> result = test.rightSideView(t_1);
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
