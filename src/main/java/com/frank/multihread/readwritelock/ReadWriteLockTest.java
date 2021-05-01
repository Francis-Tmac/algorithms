package com.frank.multihread.readwritelock;

import static java.lang.Thread.currentThread;

import javax.sound.midi.Soundbank;

/**
 * {@link  }
 *
 * @Date 2021/4/30
 * @Author frank
 * @Description:
 */
public class ReadWriteLockTest {

    private final static String text = "Thisistheexampleforreadwritelock";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int index = 0; index <  text.length(); index++){
                    try {
                        char c = text.charAt(index);
                        shareData.write(c);
                        System.out.println(currentThread() + " write " + c);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        // 创建10个线程进行数据读操作
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                while (true){
                    try {
                        System.out.println(currentThread() + " read " + new String(shareData.read()));
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
