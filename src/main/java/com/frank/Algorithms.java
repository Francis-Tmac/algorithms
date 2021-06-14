package com.frank;

import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * {@link  }
 *
 * @Date 2021/5/29
 * @Author frank
 * @Description:
 */
public class Algorithms {



    private String getCommonPreChar(List<String> stringList){
        Queue<Character> queue =new ArrayDeque();
        if(stringList.size() == 1){
            return stringList.get(0);
        }
        String firstString = stringList.get(0);
        char[] charArr = firstString.toCharArray();
        for(char i : charArr){
            queue.add(i);
        }
        List<Character> target = new ArrayList<>();
        for(int i = 1; i <= stringList.size(); i++){
            char[] temp = stringList.get(i).toCharArray();
            if(temp.length == 0){
                return "";
            }
            for(int j = 0;j<temp.length; j++){
                if(temp[j] == queue.poll()){
                    target.add(j,temp[j]);
                }else{
                    break;
                }
            }
            queue.clear();
            for(char k : target){
                queue.add(k);
            }
            target.clear();
        }
        char[] arr = new char[queue.size()];
        for(int j = 0; j < arr.length; j++){
            arr[j] = queue.poll();
        }
        return new String(arr);
    }
}
