package com.dragon.juc;

import com.dragon.juc.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/27 21:19
 * @description：CountDownLatch 减少计数
 * @modified By：
 * @version: $
 * 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 * 解释：6个同学陆续离开教室后值班同学才可以关门。
 * main主线程必须要等前面6个线程完成全部工作后，自己才能开干
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()
                        +"\t 国被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()
                +"\t 秦灭六国，一统华夏");
    }
}
