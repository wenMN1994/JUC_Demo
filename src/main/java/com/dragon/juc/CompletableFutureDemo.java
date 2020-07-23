package com.dragon.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/6 21:49
 * @description：异步回调
 * @modified By：
 * @version: $
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Demo01();
//        runAsync();
//        supplyAsync();

    }

    /**
     * 无返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run end ...");
        });

        future.get();
    }

    /**
     * 有返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> longCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });
        Long time = longCompletableFuture.get();
        System.out.println("time = " + time);
    }

    private static void Demo01() throws InterruptedException, ExecutionException {
        //同步，异步，异步回调
        //MQ消息中间件
        //同步
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(()->{
            System.out.println("CompletableFuture.runAsync");
        });
        future1.get();

        //异步回调
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
            System.out.println("");
            int a = 10/0;
            return 1024;
        });

        future2.whenComplete(
                (t,u)->{
                    System.out.println("whenComplete*****t="+t);
                    System.out.println("whenComplete*****u="+u);
                }
        ).exceptionally(
                f->{
                    System.out.println("exceptionally*****"+f.getMessage());
                    return 444;
                }
        );
    }
}
