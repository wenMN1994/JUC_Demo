package com.dragon.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
                    System.out.println("*****t="+t);
                    System.out.println("*****u="+u);
                }
        ).exceptionally(
                f->{
                    System.out.println(f.getMessage());
                    return 444;
                }
        );
    }
}
