package com.frank.multihread.immutabledsign;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * {@link  }
 *
 * @Date 2021/5/1
 * @Author frank
 * @Description:
 */
public class IntegerAccumulator {

    private final int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }
    public IntegerAccumulator add(int i){
        return new IntegerAccumulator(this, i);
    }

    // 构造新的累加器，需要用到另外一个 accumulator 和初始值
    public IntegerAccumulator(IntegerAccumulator accumulator, int init) {
        this.init = accumulator.getValue() + init;
    }
    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) throws Exception{
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        TimeUnit.SECONDS.sleep(30);

        IntStream.range(0,3).forEach(i -> new  Thread(()->{
            int inc = 0;
            while (true){
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
                System.out.println(Thread.currentThread().getName()+ " " + oldValue + " + " + inc + "=" + result);
                if(inc + oldValue != result){
                    System.out.println("ERROR: " + oldValue + " + " + inc + "=" + result);
                }
                inc++;
                slowly();
            }
        }).start());


    }

    private static void slowly(){
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
