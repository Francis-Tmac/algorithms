package com.frank;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import sun.misc.BASE64Encoder;

import java.security.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangfk5
 * @create: 2022-10-28 10:40
 **/
public class RsaGenerate {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        // 获取指定算法的密钥对生成器
//        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
//
//        // 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
//        gen.initialize(2048);
//
//        // 随机生成一对密钥（包含公钥和私钥）
//        KeyPair keyPair = gen.generateKeyPair();
//
//        // 获取 公钥 和 私钥
//        PublicKey pubKey = keyPair.getPublic();
//        PrivateKey priKey = keyPair.getPrivate();
//
//
//        // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
//        byte[] pubEncBytes = pubKey.getEncoded();
//        byte[] priEncBytes = priKey.getEncoded();
//
//        // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
//        String pubEncBase64 = new BASE64Encoder().encode(pubEncBytes);
//        String priEncBase64 = new BASE64Encoder().encode(priEncBytes);
//
//        System.out.println("pubEncBase64 key: " + pubEncBase64);
//        System.out.println("priEncBase64 key: " + priEncBase64);

//        List<Integer> list = Arrays.asList(1,2,3,4,5);
//
//        list.add(null);
//        System.out.println(list.size());
//        Integer a = 2;
//        List<Integer> list1 = list.stream().filter(a::equals).collect(Collectors.toList());
//        System.out.println(JSON.toJSON(list1));

//        System.out.println("sqoop import \\ \n--connect http:127.0.0.1:3306 \\ \n--username root");
//        String ss = "1010.00000000";
//        Integer a = Integer.valueOf(ss);
//        System.out.println(a);
        ThreadFactory threadFactory =
                new ThreadFactoryBuilder().setNameFormat("Task-Execute-Thread-%d").build();

        ExecutorService executorService =
                Executors.newFixedThreadPool(3, threadFactory);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        for(int i = 1; i < 10 ; i++){
            TaskThread taskThread = new TaskThread(i);
            ListenableFuture<String> future = listeningExecutorService.submit(taskThread);
            FutureCallback<String> futureCallback = new FutureCallback<String>() {
                @Override
                public void onSuccess(String o) {
                    // 添加执行记录 taskNodeId
                    System.out.println(Thread.currentThread().getName() + "_onSuccess_" + o);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("onFailure_" + throwable.getMessage());
                }
            };
            Futures.addCallback(future, futureCallback, listeningExecutorService);
        }

    }


}

class TaskThread implements Callable<String> {

    private final Integer taskExecuteKey;

    public TaskThread( Integer taskExecuteKey) {
        this.taskExecuteKey = taskExecuteKey;
    }

    @Override
    public String call() throws Exception {
        if(taskExecuteKey%4 == 0){
            throw new Exception("我抛出异常了 this is " + taskExecuteKey);
        }
        System.out.println(Thread.currentThread().getName() + "_this is " + taskExecuteKey + "_" + LocalDateTime.now());
        return "success" + taskExecuteKey;
    }
}
