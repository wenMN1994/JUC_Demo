package com.dragon.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/20 17:14
 * @description：线程间通信
 * @modified By：
 * @version: $
 */
//资源类
class ShareDataOne {
    private int number = 0; //初始值为零的一个变量
    private Lock lock = new ReentrantLock();
    private Condition cd = lock.newCondition();

    public  void incr() throws InterruptedException {
        lock.lock();
        try {
            //判断（防止虚假唤醒用while）
            while  (number != 0) {
                cd.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //通知
            cd.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public  void decr() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while  (number != 1) {
                cd.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //通知
            cd.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 现在两个线程
 * 操作一个初始值为0的变量
 * 实现一个线程对变量增加1，一个线程对变量减少1
 * 交替，来10轮
 * 线程 操作 资源类 2、高内聚低耦合
 * 1、判断
 * 2、干活
 * 3、通知
 *
 * 注意多线程之间的虚假唤醒
 */
public class NotifyWaitDemo {

    public static void main(String[] args) {
        ShareDataOne shareDataOne = new ShareDataOne();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDataOne.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDataOne.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDataOne.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareDataOne.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
