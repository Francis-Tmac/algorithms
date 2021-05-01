package com.frank.multihread.immutabledsign;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link  }
 *
 * @Date 2021/5/1
 * @Author frank
 * @Description:
 */
public class Immutable {
    private final List<String> list;

    public Immutable(List<String> list) {
        this.list = list;
    }
    public List<String> getList(){
        return this.list;
    }

    public static void main(String[] args) {
        Immutable immutable = new Immutable(new ArrayList<>());
        new Thread(()->{
            immutable.getList().add("ee");
            immutable.getList().add("dd");
        }).start();
        new Thread(()->{
            immutable.getList().add("ww");
            immutable.getList().add("yy");
        }).start();
        immutable.getList().forEach(System.out::println);
    }
}
