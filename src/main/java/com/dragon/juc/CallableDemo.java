package com.dragon.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/27 20:49
 * @description：Callable接口
 * @modified By：
 * @version: $
 */
//实现runnable接口获得多线程
class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

//实现Callable接口获得多线程
class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"---Callable.call()");
        return 200;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
//        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());
        FutureTask<Integer> futureTask3 = new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+"---Callable.call()");
            return 1024;
        });

//        new Thread(futureTask1,"thread1").start();
//        new Thread(futureTask2,"thread2").start();
        new Thread(futureTask3,"thread3").start();

        while (!futureTask3.isDone()){
            System.out.println("-----wait");
        }

//        System.out.println(futureTask1.get());
//        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());
    }
}

/**
 * 在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
 * 当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态。
 *
 * 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
 *
 * 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，
 * 就不能再重新开始或取消计算。get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，
 * 然后会返回结果或者抛出异常。
 *
 * 只计算一次
 * get方法放到最后
 */
