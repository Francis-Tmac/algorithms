package com.frank;

import com.alibaba.fastjson.JSON;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author : yangfk5
 * @description :
 * @since : 2023-01-03 14:27
 **/
public class TaskDTO {

    private SubTaskDTO firstTask;

    private SubTaskDTO secondTask;


    public SubTaskDTO getFirstTask() {
        return firstTask;
    }

    public void setFirstTask(SubTaskDTO firstTask) {
        this.firstTask = firstTask;
    }

    public SubTaskDTO getSecondTask() {
        return secondTask;
    }

    public void setSecondTask(SubTaskDTO secondTask) {
        this.secondTask = secondTask;
    }

    public static void main(String[] args) {
//        List<Integer> list1 = Arrays.asList(1,2);
//        List<Integer> list2 = Arrays.asList(4,5,6,10);
//        List<Integer> list3 = Arrays.asList(7,8,9);
//        List<List<Integer>> list = Arrays.asList(list1, list2, list3);
//        List<Integer> result = new ArrayList<>();
//        for(int i = 0; ; i++){
//            boolean flag = true;
//            for (List<Integer> integerList : list){
//                if(i < integerList.size()){
//                    result.add(integerList.get(i));
//                    flag = false;
//                }
//            }
//            if(flag){
//                break;
//            }
//        }
//        System.out.println(result);
        test1();
    }

    public static void test1(){
        String uuid = UUID.randomUUID().toString();
        LocalDateTime st = LocalDateTime.now() ;
        String tUid = uuid;
        Integer tv = 3;
        Long tIns = 235235235235L;
        String submitDateStr ="2023-03-02";
        String submitIdStr = "123023452";
        String result = String.format("%s=%s-%s-%s_%s-%s-%s",
                "taskInstance", "TASK", submitDateStr, tUid, tv, tIns, submitIdStr);
        String exeKey = "_:234_:loacl_mysql_test_:ops_studio";
        String jobName = result + exeKey;

        int index = jobName.indexOf("_:");
        String executeKey = jobName.substring(index + 2);
        System.out.println("jobName: " + jobName + ", executeKey: "+ executeKey);

        int lastIndex = executeKey.lastIndexOf("_:");
        String key = executeKey.substring(0, lastIndex);
        System.out.println(key);
    }


}
