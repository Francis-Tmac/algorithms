package com.frank;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : yangfk5
 * @description :
 * @since : 2023-02-18 11:24
 **/
@Slf4j
public class TestExecutors {



    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory =
                new ThreadFactoryBuilder().setNameFormat("MULTI_TASK_THREAD_%d").build();
        ExecutorService executorService =
                Executors.newFixedThreadPool(1, threadFactory);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        Integer size = 200;
        CountDownLatch count = new CountDownLatch(size);
        AtomicInteger atomicInteger = new AtomicInteger(size);
        for (int i = 0; i < size; i++) {
            TaskExeThread taskThread = new TaskExeThread(i);
            ListenableFuture<String> future = listeningExecutorService.submit(taskThread);
            FutureCallback<String> futureCallback = new FutureCallback<String>() {
                @Override
                public void onSuccess(String o) {
                    atomicInteger.getAndDecrement();
                    count.countDown();

//                    log.info("{} 成功了", o);
                }

                @Override
                public void onFailure(@NotNull Throwable throwable) {
                    count.countDown();
//                    log.info("失败了");
                }
            };
            Futures.addCallback(future, futureCallback, listeningExecutorService);
        }

        count.await();
        if(atomicInteger.get() > 0){
//            log.info("this count is {}", atomicInteger.get());
        }
        executorService.shutdown();
//        log.info("运行结束了");
    }


}


class TaskExeThread implements Callable<String> {

    private Integer param;

    public TaskExeThread(Integer param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        if(param % 2 == 0){
            throw new Exception("有异常");
        }
        return param.toString();
    }
}