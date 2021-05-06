package com.frank.multihread.future;

import java.util.concurrent.TimeUnit;

/**
 * {@link  }
 *
 * @Date 2021/5/1
 * @Author frank
 * @Description:
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException{
        FutureTest test = new FutureTest();
        test.callback();
    }

    private void noReturnSub() throws InterruptedException{
        FutureService<Void, Void> service = FutureService.newService();

        Future<?> future = service.submit(() -> {
            try{

                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i am finish done.");
        });
        future.get();
    }

    private void submit() throws InterruptedException{
        FutureService<String, Integer> service = FutureService.newService();

        Future<Integer> future = service.submit(input -> {
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return input.length();
        }, "Hello");

        System.out.println(future.get());
    }

    private void callback() throws InterruptedException{
        FutureService<String, Integer> service = FutureService.newService();

        Future<Integer> future = service.submit(input -> {
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return input.length();
        }, "Hello", System.out::println);

    }

}
