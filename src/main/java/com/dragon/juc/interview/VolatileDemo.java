package com.dragon.juc.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/7/2 15:19
 * @description：验证volatile在多线程并发编程中的【保证可见性&不保证原子性】
 * @modified By：
 * @version: $
 */
class MyData{
    // number 添加了volatile关键字修饰
    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    // 请注意 number 添加了volatile关键字修饰，volatile不保证原子性
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        // number 添加了volatile关键字修饰导致最终结果不确定，说明volatile不保证原子性
        System.out.println(Thread.currentThread().getName() + "\t number 添加了volatile关键字修饰后的值：" + myData.number);
        // 使用AtomicInteger可以保证原子性
        System.out.println(Thread.currentThread().getName() + "\t 使用AtomicInteger的值：" + myData.atomicInteger);
        
        // 验证volatile保证可见性
        // seeOkByVolatile(myData);
    }

    /**
     * 验证volatile保证可见性
     * @param myData
     */
    private static void seeOkByVolatile(MyData myData) {
        new Thread(()->{

            System.out.println(Thread.currentThread().getName() + "\t 线程进来了");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();

            System.out.println(Thread.currentThread().getName() + "\t 更新了number的值" + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t 程序结束。。。");
    }
}
