package com.dragon.juc;

import java.util.concurrent.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/31 10:37
 * @description：ThreadPool线程池
 * @modified By：
 * @version: $
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()
                            +"\t 号业务员办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void ThreadPool() {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(3);// 银行网点3个窗口
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();// 银行网点1个窗口
        ExecutorService threadPool3 = Executors.newCachedThreadPool();// 银行网点可扩展窗口

        try {
            for (int i = 1; i <= 30; i++) {
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()
                            +"\t 号业务员办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool3.shutdown();
        }
    }
}
