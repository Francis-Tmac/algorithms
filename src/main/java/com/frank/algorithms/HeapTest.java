package com.frank.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * {@link  }
 *
 * @Date 2021/3/2
 * @Author frank.yang
 * @Description:
 */
public class HeapTest {

    // 100kb

    byte[] a = new byte[1024*100];


    public static void main(String[] args) throws Exception{
        List<HeapTest> list = new ArrayList<>();
        while (true){
            list.add(new HeapTest());
            TimeUnit.MILLISECONDS.sleep(10);
        }

    }
}
