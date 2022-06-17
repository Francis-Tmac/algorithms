package com.frank.algorithms;

/**
 * @author fukangyang
 * @date 2022/6/15
 * @ desc
 */

public class CharDance {

    /**
     * 题目1：
     * 给定一个随机的初始字符串: src，给定一组随机的指令: ops，(src 和 ops 一定是合法的)，求经过转换后得到的新字符串。
     * ops: M:3,I:7/2,X:o/h
     */
    public String transfer(String src, String ops) {
        String[] strings = ops.split(",");
        // 处理移动字符串
        String moveString = strings[0];
        int moveLength = Integer.parseInt(moveString.substring(2));
        String move = this.moveToRight(src,moveLength);
        // help gc
        src = null;
        // 处理下标交换
        String[] indexArr = strings[1].substring(2).split("/");
        String indexChange = this.indexChange(move, Integer.parseInt(indexArr[0]), Integer.parseInt(indexArr[1]));
        // help gc
        move = null;
        // 处理字符交换
        // 处理下标交换
        String[] exchangeArr = strings[2].substring(2).split("/");
        return this.eXchange(indexChange, exchangeArr[0].toCharArray()[0],exchangeArr[1].toCharArray()[0]);
    }

    public String transferMultipleTimes(String src, String ops, long count) {
        // show me your code
        for(int i = 0; i < count ; i++){
            src = transfer(src, ops);
        }
        return src;
    }

    public static void main(String[] args) {
        CharDance charDance = new CharDance();

        String src = "wosjgncxyakdbefh";
        String ops = "M:3,I:7/2,X:o/h";
        // kdoefgwbsjhncxya
        System.out.println(charDance.transfer(src,ops));
    }


    /**
     * 假设 两个字符在字符串中都存在
     * @param src
     * @param fromChar
     * @param toChar
     * @return
     */
    private  String eXchange(String src, char fromChar, char toChar){
        char[] arr = src.toCharArray();
        int length = arr.length;
        for(int i = 0 ; i < length; i++){
            if(arr[i] == fromChar){
                arr[i] = toChar;
            }else if (arr[i] == toChar){
                arr[i] = fromChar;
            }
        }
        return new String(arr);
    }

    /**
     * 按位置交换
     * @param src
     * @param original
     * @param target
     * @return
     */
    private  String indexChange(String src, int original, int target){
        char[] arr = src.toCharArray();
        int length = arr.length;
        if(original > length || target > length){
            // 或者抛出异常
            return src;
        }
        char tempChar = arr[target];
        arr[target] = arr[original];
        arr[original] = tempChar;
        return new String(arr);
    }

    /**
     * 将字符串向右移动 count
     * @param src
     * @param count
     * @return
     */
    private  String moveToRight(String src, int count){
        if(src == null || src.length() == 0){
            return "";
        }
        char[] original = src.toCharArray();
        int length = original.length;
        int tempLength = count%length;
        // 如果移动长度为字符长度的n 倍直接返回
        if(tempLength == 0){
            return src;
        }
        char[] temp = new char[tempLength];
        int beginTempIndex = length - tempLength ;
        // 先取出尾部需要移动的长度
        for(int i = beginTempIndex; i < length; i++){
            temp[i - beginTempIndex] = original[i];
        }
        // 将前置的字符移到后面
        for(int j = length - 1; j >= tempLength; j--){
            original[j] = original[j - tempLength];
        }
        // 填充左侧空闲的字符
        for(int k = 0; k < tempLength; k++){
            original[k] = temp[k];
        }
        return new String(original);
    }


}
