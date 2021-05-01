package com.frank.multihread.thread;

import java.util.Arrays;
import java.util.List;

/**
 * {@link  }
 *
 * @Date 2021/4/27
 * @Author frank
 * @Description:
 */
public class ActiveLoadTest {
    static{
        i = 20;
    }

    static volatile int i = 10;

    static Child child = new Child();
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java","Thread","Concurrency","Scala","Clojure");

        list.parallelStream().map(String::toUpperCase).forEach(o->{
            System.out.println((Thread.currentThread().getName() + "----" + o));
        });
        list.forEach(System.out::println);
    }
}
