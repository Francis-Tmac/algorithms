package com.frank.algorithms;

import java.util.*;

/**
 * {@link  }
 *
 * @Date 2021/3/8
 * @Author frank.yang
 * @Description:
 */
public class Interview {

    private static boolean is(String ss) {
        if (ss.isEmpty()){
            return false;
        }
        char[] chars = ss.toCharArray();
        if (chars.length == 1){
            return true;
        }
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }


    private static int[] getTarget(int[] array, int target) {
        int[] arr = new int[]{-1};
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return arr;
    }

    static class TreeNode {

        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /***
     * 二叉树的层序遍历
     ***/
    private static List<List<Integer>> levelSort(TreeNode node) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            boolean flag = false;
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    level.add(null);
                    continue;
                }
                flag = true;
                level.add(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if (flag && !level.isEmpty()) {
                result.add(level);
            }
            level = null; // help gc
        }
        return result;
    }

    private static void levelArray(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()){

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                System.out.println(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null){
                    queue.offer(temp.right);
                }
                temp = null; // help gc
            }
        }
    }

    private static void lastPrint(TreeNode node){

        if(node.left != null){
            lastPrint(node.left);
        }
        System.out.println(node.val);
        if(node.right != null){
            lastPrint(node.right);
        }


    }

    private static void midPrintStack(TreeNode node){
        Deque<TreeNode>  stack = new LinkedList<>();
        stack.push(node);
        Set<TreeNode> set = new HashSet<>();
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.left != null && !set.contains(temp.left)){
                stack.push(temp);
                stack.push(temp.left);
                continue;
            }else {
                System.out.println(temp.val);
                set.add(temp);
            }
            if(temp.right != null){
                stack.push(temp.right);
            }
            temp = null;
        }
    }

    private static void lastPrintStack(TreeNode node){
        Deque<TreeNode> stack = new LinkedList<>();
        Set<TreeNode> hasSet = new HashSet<>();
        stack.push(node);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.left != null && !hasSet.contains(temp.left)){
                stack.push(temp);
                stack.push(temp.left);
                continue;
            }
            if(temp.right != null && !hasSet.contains(temp.right)){
                stack.push(temp);
                stack.push(temp.right);
                continue;
            }
            System.out.println(temp.val);
            hasSet.add(temp);
        }


    }

    public static void main2(String[] args) {
        int n = 3;
        if ( (n & 1) == 1){
            
        }
        System.out.println(1/2);
    }


    public static void main1(String[] args) {
        int[] b = new int[]{1, 2, 3, 4, 5, 6, 7};

        int[] arr = twoSum(b, 13);
//        System.out.println(arr);
        TreeNode node_6 = new TreeNode(6);
        TreeNode node_5 = new TreeNode(5,null, node_6);

        TreeNode node_4 = new TreeNode(4);
        TreeNode node_3 = new TreeNode(3, node_4, null);
        TreeNode node_2 = new TreeNode(2, null,node_5);
        TreeNode node_1 = new TreeNode(1, node_2, node_3);

//        levelArray(node_1);
        List<List<Integer>> list = levelSort(node_1);
        List<Integer> integerList = new ArrayList<>();
        int[] aux = integerList.stream().mapToInt(Integer::valueOf).toArray();

        System.out.println(list);
        list.get(0);

//        lastPrint(node_1);

        lastPrintStack(node_1);
//        list.get(0);
    }


//        int[] a = new int[]{1,2,3};
//        int[] b = new int[]{4,5,6,7};
//        int[] c = mergeSort(a,b);
//        System.out.println(c[0]);

//        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,4,7));
//        List<Integer> list2 = new ArrayList<>(Arrays.asList(2,5,8,11));
//        List<Integer> list3 = new ArrayList<>(Arrays.asList(14,19,20,22));
//        List<Integer> list4 = new ArrayList<>(Arrays.asList(16,23,25,30,33));
//        List<Integer> list5 = new ArrayList<>(Arrays.asList(3,6,9,12,23));
//
//        List<List<Integer>> listList = new ArrayList<>(Arrays.asList(list1,list2,list3,list4,list5));
//        List<Integer> targetList = mergeList(listList).get(0);
//
//        System.out.println(targetList);

//    }

    private static List<List<Integer>> mergeList(List<List<Integer>> originList){
        int listSize = originList.size();
        if(listSize == 1){
            return originList;
        }
        List<List<Integer>> newList = new ArrayList<>();

        for (int i = 0; i < (listSize + 1) / 2; i++){
            if(i == listSize-1-i){
                newList.add(originList.get(i));
            }else{
                newList.add(mergeSort(originList.get(i),originList.get(listSize-1-i)));
            }
        }
        newList = mergeList(newList);
        return newList;
    }
    // 合并两个 链表
    private static List<Integer> mergeSort(List<Integer> arrA, List<Integer> arrB){
        List<Integer> array = new ArrayList<>();
        int size = arrA.size() + arrB.size();
        if(size == 0){
            return array;
        }
        int a = 0;
        int b = 0;
        for(int i = 0 ; i < size ; i++){
            if(a >= arrA.size()){
                array.add(i, arrB.get(b));
                b++;
                continue;
            }
            if(b >= arrB.size()){
                array.add(i, arrA.get(a));
                a++;
                continue;
            }
            if(arrA.get(a) < arrB.get(b)){
                array.add(i,arrA.get(a));
                a++;
            }else{
                array.add(i, arrB.get(b));
                b++;
            }
        }
        return array;
    }

    private static int[] mergeSort(int[] arrA, int[] arrB){
        int size = arrA.length + arrB.length;
        if(size == 0){
            return new int[]{0};
        }
        int[] array = new int[arrA.length + arrB.length];
        int a = 0;
        int b = 0;
        for(int i = 0 ; i < size ; i++){
            if(a >= arrA.length){
                array[i] = arrB[b];
                b++;
                continue;
            }
            if(b >= arrB.length){
                array[i] = arrA[a];
                a++;
                continue;
            }
            if(arrA[a] < arrB[b]){
                array[i] = arrA[a];
                a++;
            }else{
                array[i] = arrB[b];
                b++;
            }
        }
        return array;
    }

    public  static int[] twoSum2(int[] nums, int target) {
        HashMap map = new HashMap();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],i);
        }
        for(int i = 0; i < nums.length; i++){
            Integer j = target - nums[i];
            if(map.get(j) != null){
                if(i != (int)map.get(j)){
                    return new int[]{i,(int)map.get(j)};
                }
            }
        }
        return null;
    }

    public static int maxSubArray(int[] nums){
        int pre = 0, maxAns = nums[0];
        for(int x : nums){
            pre= Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static int sum(int x, int y){
        int  z = x | y;
        return z;
    }

//    public static void main(String[] args) {
//        int[] b = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int max = maxSubArray(b);
//
//        System.out.println(max);

//        int[] a= new int[]{-1,0,1,2,-1,-4};
//        List<List<Integer>>  b = threeSum(a);
//        b.get(1);
//        System.out.println(sum(3,2));
//
//        int[] arr = twoSum(b, 13);
//        System.out.println(arr);
//    }

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

    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums.length == 0 || nums.length == 1){
            return null;
        }
        Arrays.sort(nums);
        List<List<Integer>> targetList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                targetList.add(new ArrayList<>());
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

//    public char firstUniqChar(String s) {
//        char[] arr = s.toCharArray();
//        int length = arr.length;
//        for(int i = 0; i < length; i++){
//            char temp= char[i];
//            for(int j = i + 1; i < length; j++){
//                if(temp == arr[j]){
//                    arr[j] = ''
//                    break;
//                }else{
//
//                }
//            }
//        }
//    }

    /***
     * nums = [100,4,200,1,3,2]
     * @ param nums
     * @ return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums){
            set.add(n);
        }

        int max = 0,cur = 0 , length = nums.length;

        for(int i = 0 ; i < length; i++){
            if(set.contains(nums[i] - 1)){

                continue;
            }
            cur = 1;
            int temp = nums[i];
            while(cur != 0){
                if(set.contains(++temp)){
                    cur ++;
                    max = Math.max(max, cur);
                }else{
                    cur = 0;
                }
            }
        }
        return max;
    }

    public String replaceSpace(String s) {
        int length = s.length();
        char[] chars = new char[length * 3];
        int size = 0;
        for(char c : s.toCharArray()){
            if(c == ' '){
                chars[size++] = '%';
                chars[size++] = '0';
                chars[size++] = '0';
            }else {
                chars[size++] = c;
            }
        }
        return new String(chars, 0 , size);
    }

    public static char firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            if(map.get(c) == null){
                map.put(c, 1);
            }else {
                map.put(c, map.get(c) + 1);
            }
        }
        for (char c : s.toCharArray()){
            if(map.get(c) == 1){
                return c;
            }
        }
        return ' ';
    }

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> target = new ArrayList();
//        LinkedList<TreeNode> queue = new LinkedList();
//        queue.poll(root);
//        while(queue.size() > 0){
//            int length = queue.size();
//            List<Integer> list = new ArrayList();
//            for(int i = 0; i < length; i++){
//                TreeNode temp = queue.offer();
//                list.add(temp.val);
//                if(temp.left != null){
//                    queue.poll(temp.left);
//                }else if(temp.right != null){
//                    queue.poll(temp.right);
//                }
//
//            }
//            target.add(list);
//        }
//        return target;
//    }



    public static boolean isStraight(int[] nums) {
        Map<Integer,Integer> map = new HashMap();
        for(int n : nums){
            if(map.get(n) == null){
                map.put(n, 1);
            }else {
                map.put(n, map.get(n) + 1);
            }
        }
        int length = nums.length;
        boolean flag = false;
        for(int i = 0; i < length && !flag; i++){
            int cur = nums[i];
            if(cur == 1){

            }else if(cur == 0 || map.get(cur - 1) != null){
                continue;
            }

            for(int j = 1; j < 5; j++){
                Integer temp = map.get(cur + j);
                if(temp != null && temp == 1){
                    if(j ==  4){
                        flag = true;
                    }
                    continue;
                }else if(temp == null ){
                    Integer zeroNum = map.get(0);
                    if(zeroNum != null && zeroNum != 0){
                        map.put(0, zeroNum - 1);
                        continue;
                    }
                }else{
                    break;
                }

            }
        }
        return flag;
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] aux = new int[k];
        int length = arr.length;
        if(k == 0) {
            return arr;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for(int i = 0; i < k; i++){
            queue.offer(arr[i]);
        }
        for(int i = k; i < length; i++){
            int temp = queue.peek();
            if(temp > arr[i]){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for(int i = 0; i < k; i++){
            aux[i] = queue.poll();
        }
        return aux;
    }

    public String reverseLeftWords(String s, int n) {
        if(n == 0) return s;
        char[] temps = new char[n];
        char[] arr = s.toCharArray();
        int length = arr.length;
        for(int i = 0; i < n; i++){
            temps[i] = arr[i];
        }
        for(int i = 0; i < length - n; i++){
            arr[i] = arr[i + n];
        }
        for(int i = 0; i < n; i++){
            arr[length - n + i] = temps[i];
        }
        return new String(arr);
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for(int i = 0; i < k; i++){
            minHeap.offer(nums[i]);
        }
        int length = nums.length;
        for(int j = k; j < length; j++){
            int temp = minHeap.peek();
            if(temp < nums[j]){
                minHeap.poll();
                minHeap.offer(nums[j]);
            }
        }

        return minHeap.peek();

    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4},2));
    }

    public static void main3(String[] args) {
//        int[] nums = new int[]{9,3,2,1,4,6,8,5};
//        System.out.println(getLeastNumbers(nums,3));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        System.out.println(minStack.min);
        minStack.pop();
        System.out.println(minStack.min);
        minStack.pop();
        System.out.println(minStack.min);
        minStack.pop();
        System.out.println(minStack.min);
    }
}
class MinStack {
    int[] arr;
    int min;

    int curIndex;

    int size;

    /** initialize your data structure here. */
    public MinStack() {
        arr = new int[1024];
    }

    public void push(int x) {
        arr[curIndex++] = x;
        if(size++ == 0){
            min = x;
        }else{
            min = Math.min(min, x);
        }
    }

    public void pop() {
        int temp = arr[curIndex - 1];
        String s = "dfsadf";
        Set<Character> set = new HashSet<>();

        if(temp == min){
            min = arr[0];
            for(int i = 1; i < curIndex - 1  ; i++){
                min = Math.min(min, arr[i]);
            }
        }
        curIndex--;
    }

    public int top() {
        return arr[curIndex - 1];
    }

    public int min() {
        return min;
    }
}