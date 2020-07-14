package com.dragon.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/7/14 10:55
 * @description：生产者消费者模式传统版
 * @modified By：
 * @version: $
 */
class ShareData {

    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {

            //判断
            while (num != 0) {
                //不满足条件，等着
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void deIncrement() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num == 0){
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 一个初始值为0的变量 两个线程交替操作 一个加1 一个减1来5轮
 */
public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.deIncrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}
