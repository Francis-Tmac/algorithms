package com.frank;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link  }
 *
 * @Date 2021/5/16
 * @Author frank
 * @Description:
 */
public class Generic<T> {

    // 编译错误泛型类中今天太方法和静态变量不可以
    // 使用泛型类所申明的泛型类型参数
    // 泛型类中的泛型参数的实例化是在定义泛型对象的时候指定的。
    // 静态变量不需要使用对象来调用。对象没有创建，如何确定泛型参数是何种类型
 /*   static T item;

    static T show(T one){
        return null;
    }*/


    // 泛型方法，在泛型方法中使用的T是自己再方法中定义的T ,而不是泛型类中定义的T
    public static<T> T show(T one){
        return null;
    }

    public static void fillNumberList(List<? extends Number> list) {
//        list.add(new Integer(0));//编译错误
//        list.add(new Integer(3));//编译错误

         }


    private static final String HINT_START = "-- DATAMAX_HINT_START:dev_ignore\n";

    private static final String HINT_END = "\n-- DATAMAX_HINT_END";

    private static Pattern HINT_PATTERN =
            Pattern.compile("("+HINT_START+")((.|\\n)+? )("+HINT_END+")");

    private static final Pattern DYNAMIC_INDEX_REGEX = Pattern.compile("\\{.+?\\|.+}");
    public static void main(String[] args) throws Exception{
//        fib(5);
//        ExecutorService service = Executors.newCachedThreadPool();
//
//        ExecutorService service1 = Executors.newFixedThreadPool(3);
//
//        ExecutorService service2 = Executors.newSingleThreadExecutor();
//
//        ExecutorService service3 = Executors.newScheduledThreadPool(4);

//        Queue<Integer> queue = new ArrayBlockingQueue<>(5);
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//        System.out.println(queue.poll());

        String ddd = "-- DATAMAX_HINT_START:dev_ignore\n" +
                " sfsdf\n" +
                "sdf " +
                "  \n-- DATAMAX_HINT_END";

        String tttt = "sfasdfasdf -- DATAMAX_HINT_START:dev_ignore\n" +
                "sfsdf\n" +
                "-- DATAMAX_HINT_START:dev_ignore\n" +
                "-- DATAMAX_HINT_END\n" +
                "sfsdf\n" +
                "sdf \n" +
                "-- DATAMAX_HINT_ENDs safasdfsdf";




//        String jobName = "322_mcsp_smc_05_smc_so_line_202112_1722188243495075842";
//
//        String ddd = jobName.substring(0, jobName.lastIndexOf("_"));
//        System.out.println(ddd);


    }

    public static int fib(int n) {
        if(n == 0){
            return 0;
        }
        int[] arr = new int[n+1];
        int i = 0;

        while(i <= n){
            if(i == 0){
                arr[i] = 0;
            }else if(i == 1){
                arr[i] = 1;
            }else{
                arr[i] = arr[i-1] + arr[i-2];
            }
            i++;
        }
        return arr[n];
    }

}
